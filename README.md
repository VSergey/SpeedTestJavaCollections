# SpeedTestJavaCollections
Performance test of Java collections from different frameworks.

I used [JMH](http://java-performance.info/jmh/) 1.17.3 in tests grouped by types and common operations.
For measure memory utilization used [JOL](http://openjdk.java.net/projects/code-tools/jol/)

Currently tested next frameworks:
- JDK 8 windows 64 bit
- [Apache Commons Collections](http://commons.apache.org/proper/commons-collections/) (4.0)
- [Eclipse Collections](https://www.eclipse.org/collections/) (7.0.1)
- [HPPC](http://labs.carrotsearch.com/hppc.html) (0.7.1)
- [Koloboke](https://github.com/OpenHFT/Koloboke) (1.0.0)
- [FastUtil](http://fastutil.di.unimi.it/) (7.0.13)
- [Trove](http://trove.starlight-systems.com/) (3.0.3)

See results of testing on [wiki](https://github.com/VSergey/SpeedTestJavaCollections/wiki)
