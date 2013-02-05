package de.doridian.ccftp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

import net.minecraft.server.MinecraftServer;

public class Proxy {
    public Proxy() {
    }

    public void startFTP() {
        new Thread() {
            public void run() {
                MinecraftServer server = MinecraftServer.getServer();
                while (server.getNetworkThread() == null) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                }

                if (CCFTP.ftp_ip == null || CCFTP.ftp_ip.isEmpty()) {
                    CCFTP.ftp_ip = server.getServerHostname();
                }

                if (CCFTP.ftp_passive_local_address == null || CCFTP.ftp_passive_local_address.isEmpty()) {
                    try {
                        Socket sock = new Socket("8.8.8.8", 53);
                        String addr = sock.getLocalAddress().getHostAddress();
                        sock.close();
                        URLConnection urlConnection = new URL("http://system.doridian.de/ip.php").openConnection();
                        urlConnection.setRequestProperty("User-Agent",
                                "Mozilla/5.0 (Windows NT 6.2; rv:9.0.1) Gecko/20100101 Firefox/9.0.1");
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(urlConnection.getInputStream()));
                        String newAddr = reader.readLine();
                        if (!newAddr.equalsIgnoreCase(addr)) {
                            CCFTP.ftp_passive_local_address = newAddr;
                            System.out.println("Autodetected passive local address to be: " + newAddr);
                        }
                        reader.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("Starting CCFTPd on " + CCFTP.ftp_ip + ":" + CCFTP.ftp_port + " (default world \""
                        + CCFTP.default_world + "\", using passive ports " + CCFTP.ftp_passive_ports + ")");

                try {
                    new ComputerCraftFTP(CCFTP.ftp_port, CCFTP.ftp_ip, CCFTP.ftp_passive_ports,
                            CCFTP.ftp_passive_local_address, CCFTP.ftp_max_file_size);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
