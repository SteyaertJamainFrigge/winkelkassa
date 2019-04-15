# winkelkassa
een winkelkassa


# guide for developing app on linux

### installing mysql
command: <br />
`$ sudo apt-get update`<br/>
`$ sudo apt-get install mysql-server`<br />

### installing jdk & Nebeands
#### install JDK
1. To install Java 8 JDK version, first add following webupd8team/java PPA to your system and update the repository package database as shown.<br />
&nbsp;&nbsp;&nbsp;`$ sudo add-apt-repository ppa:webupd8team/java`<br/>
&nbsp;&nbsp;&nbsp;`$ sudo apt-get update`

2. Once PPA has been added and updated, now search for the packages with name oracle-java8 and install it as shown.<br />
&nbsp;&nbsp;&nbsp;`$ apt-cache search oracle-java8`<br />
&nbsp;&nbsp;&nbsp;`$ sudo apt-get install oracle-java8-installer`<br />
If you have more than one Java installed on your system, you can install oracle-java8-set-default package to set Java 8 as default as shown.<br />
&nbsp;&nbsp;&nbsp;`$ sudo apt-get install oracle-java8-set-default`<br />
Please note that the same webupd8team/java PPA also offers older and newer versions of Java packages like Java 7 and Java 9.

#### Install NetBeans IDE in Linux Mint
3. Now oen a browser, navigate to NetBeans IDE download page and download the latest NetBeans IDE installer script for your installed Linux distribution.<br />
Alternatively, you can also download NetBeans IDE installer script in your system via wget utility, by issuing the below command.<br />
&nbsp;&nbsp;&nbsp;`$ wget -c http://download.netbeans.org/netbeans/8.2/final/bundles/netbeans-8.2-linux.sh`

4. After the download completes, navigate to the directory where the NetBeans IDE installer has been downloaded and issue the below command to make the installer script executable and start installing it.<br />
&nbsp;&nbsp;&nbsp;`$ chmod +x netbeans-8.2-linux.sh`<br/>
&nbsp;&nbsp;&nbsp;`$ ./netbeans-8.2-linux.sh`

5. After running the installer script above, the installer “Welcome page” will show up as follows, click Next to continue (or customize your installation by clicking on Customize) to follow the installation wizard.<br />
**!! Kies in de wizard om de JDK te gebruiken die je juist hebt geïnstalleerd (v 1.8.0.201) !!**

### installing git
command: <br />
&nbsp;&nbsp;&nbsp;`$ sudo apt-get install git`

### cloning the repository with git
1. create a folder where you want to store all your netbeans projects

2. go to that folder with terminal

3. run this command:
&nbsp;&nbsp;&nbsp;`$ git clone https://github.com/SteyaertJamainFrigge/winkelkassa.git`

4. give your github credentials

_here are some git tutorials that explain more about what you can do:_ <br/>
&nbsp;&nbsp;&nbsp;https://www.tutorialspoint.com/git/<br/>
&nbsp;&nbsp;&nbsp;https://www.atlassian.com/git/tutorials<br/>
&nbsp;&nbsp;&nbsp;https://guides.github.com/activities/hello-world/


### opening the project with netbeans
1. double click on the netbeans icon that was created by instal wizard

2. go to tools -> plugin -> available plugins and search for "gradle" and "git toolbar"

3. install these plugins (git toolbar is optional)

4. go to File -> open project and navigate to your cloned project

# configure DB
run de winkelkassa.sql in mysql <br/>
add user with username: "kassa_usr" passwrd: "kassa_pwd" that has all ddl access to the db.

