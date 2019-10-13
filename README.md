# Karatek TeamspeakBot

A small TS3 server query bot written in java, for an easy to use TS3 cli. Currently, it is in an early state of development and this file ist work in progress too.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites

You will have to install Java < 8.
On openSUSE leap 15.1, you can do this by typing 

```
sudo zypper install java-1_8_0-openjdk-headless
```

### Installing

A step by step series of examples that tell you how to get a running version of KTS.

First of all, download a .jar file from 'releases'.


```
wget https://github.com/KaratekHD/TeamspeakBot/releases/download/v1.1-alpha.1/TeamspeakBot.jar
```

Then run the jar:
```
java -jar TeamspeakBot.jar
```
If you are behind a http proxy, try this: (Replace the IP addresses with your own proxy)
```
java -DsocksProxyHost=10.208.81.1 -DsocksProxyPort=1080 -Dhttp.proxyHost=192.168.2.110 -Dhttp.proxyPort=3128 -Dhttps.proxyHost=192.168.2.110 -Dhttps.proxyPort=3128 -jar TeamspeakBot.jar
```

After this, the setup wizard will guide you through the configuration procedure.

## Built With

* [TeamSpeak-3-Java-API](https://github.com/TheHolyWaffle/TeamSpeak-3-Java-API) - The TS3 API used


## Contributing
NOT IMPLEMENTED YET!

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning
We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors
* **KaratekHD** - *Main development* - [KaratekHD](https://github.com/KaratekHD)
* **Billie Thompson** - *README.md template* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/KaratekHD/TeamspeakBot/contributors) who participated in this project.

## License

This project is licensed under the GNU General Public License v3 - see the [LICENSE.md](LICENSE) file for details

