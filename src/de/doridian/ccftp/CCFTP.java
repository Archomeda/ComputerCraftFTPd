package de.doridian.ccftp;

import java.util.logging.Level;

import net.minecraftforge.common.Configuration;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "CCFTP", name = "ComputerCraftFTPd", version = "1.0")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class CCFTP {
    @Instance("CCFTP")
    public static CCFTP instance;

    @SidedProxy(clientSide = "de.doridian.ccftp.ClientProxy", serverSide = "de.doridian.ccftp.Proxy")
    public static Proxy proxy;

    public static int ftp_port;
    public static String ftp_ip;
    public static String ftp_passive_ports;
    public static String ftp_passive_local_address;
    public static String ftp_max_file_size;
    public static String default_world = "world";

    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());

        try {
            config.load();
            ftp_port = config.get(Configuration.CATEGORY_GENERAL, "ftp_port", 2221).getInt();
            ftp_ip = config.get(Configuration.CATEGORY_GENERAL, "ftp_ip", "").value;
            ftp_passive_ports = config.get(Configuration.CATEGORY_GENERAL, "ftp_passive_ports", "40000-50000").value;
            ftp_passive_local_address = config.get(Configuration.CATEGORY_GENERAL, "ftp_passive_local_address", "").value;
            ftp_max_file_size = config.get(Configuration.CATEGORY_GENERAL, "ftp_max_file_size", "1MB").value;
            default_world = config.get(Configuration.CATEGORY_GENERAL, "default_world", "world").value;
        } catch (Exception e) {
            FMLLog.log(Level.SEVERE, e, "ComputerCraftFTPd has encountered a problem while loading its configuration");
        } finally {
            config.save();
        }
    }

    @Init
    public void load(FMLInitializationEvent event) {
        proxy.startFTP();
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) {
    }
}
