# ComputerCraftFTPd #
ComputerCraftFTPd is a mod for Minecraft servers that adds the ability for clients to remotely access their ComputerCraft computers through FTP.

This mod was originally developed for Bukkit (MC 1.2.3) by Doridian ([ComputerCraft.info](http://www.computercraft.info/forums2/index.php?/topic/279-bukkitsmpcomputercraft-ftp/), [GitHub](https://github.com/Doridian/ComputerCraftFTPd)). This fork works with Forge for MC 1.4.7.

Please note that this readme is provided "as is" and may contain incorrect and/or incomplete information. If something is not correct, please file an issue explaining what's wrong or missing.


## Setting up the build environment ##
*Note: In this section, [Eclipse](http://www.eclipse.org/) will be used as example.*

1. Clone or download the source of ComputerCraftFTPd.
2. Import the downloaded source into Eclipse.
3. Download the source of [Minecraft Forge](http://files.minecraftforge.net/) build #497 (or the latest build), extract it somewhere (preferably somewhere near the source) and execute `install.bat` (Windows) or `install.sh` (Linux).
4. Compile it once (see below) in order to get all required dependencies.
5. Import the Minecraft project of MCP into Eclipse (can be found in `forge/mcp/eclipse/Minecraft`) and rename it to Forge.
6. Check if the path variable `MCP_LOC` is pointing to the correct directory (`forge/mcp`). If you have linked the Minecraft project, this variable should be set to `${PROJECT_LOC}/../..`, which means it will look two directories up from the project directory.
7. Refresh the projects in order to load and detect their contents properly.


## Compiling ##
*Note: Only tested with Forge #497, it is not guaranteed that it works with other builds of Forge.*

1. Must have followed all steps until step 4 of setting up the build environment (see above).
2. Download [Apache Ant](http://ant.apache.org/) and install it somewhere where you can easily find it later.
3. Inside the `ComputerCraftFTPd` directory, create a file named `build.properties` with the following properties:
  - `mcp.home=<relative_path_to_mcp_dir>` (most common: `forge/mcp` or `../forge/mcp`)
  - `version.minecraft=<version>` (Minecraft version, current: 1.4.7)
  - `version=<version>` (CCFTPd version, last: 1.0)
4. Run `ant build` and let it run for a while. Please note that an active internet connection is required when running this for the first time. As it will download Maven first in order to resolve and download the dependencies. After that, it will automatically compile and obfuscate the code.
5. If successful, inside the `build` directory, you should see a new .zip file with your version numbers in its name.

## Third party dependencies ##
ComputerCraftFTPd uses [Apache FtpServer](http://mina.apache.org/ftpserver-project/index.html) from the Apache MINA Project as its backend FTP server. This dependency will be downloaded automatically when compiling.