# Gradle Memory Configuration for Codespaces

This document explains the memory optimizations implemented to resolve Gradle daemon crashes in resource-constrained environments like GitHub Codespaces.

## Problem

On codespaces with 2 cores and 8GB of RAM, the Gradle daemon would frequently disappear during builds due to excessive memory allocation:

- **Original Settings:**
  - Gradle daemon: 3GB heap (`org.gradle.jvmargs=-Xmx3072M`)
  - Kotlin daemon: 3GB heap (`kotlin.daemon.jvmargs=-Xmx3072M`)
  - **Total:** 6GB on an 8GB system (75% allocation)

This left insufficient memory for the operating system and other processes, causing the Linux OOM killer to terminate the Gradle daemon.

## Solution

Memory allocations have been optimized for 8GB environments:

- **New Settings:**
  - Gradle daemon: 2GB heap (`org.gradle.jvmargs=-Xmx2048M`)
  - Kotlin daemon: 1.5GB heap (`kotlin.daemon.jvmargs=-Xmx1536M`)
  - **Total:** 3.5GB on an 8GB system (44% allocation)

This provides 4.5GB of headroom for the OS, file system cache, and other processes.

## Configuration File

The memory settings are configured in `gradle.properties`:

```properties
# Kotlin daemon memory allocation
kotlin.daemon.jvmargs=-Xmx1536M

# Gradle daemon memory allocation  
org.gradle.jvmargs=-Xmx2048M -Dfile.encoding=UTF-8
```

## Verification

To verify the daemon is running with correct memory settings:

```bash
# Check daemon status
./gradlew --status

# Monitor memory usage
ps aux | grep gradle
```

Look for the `-Xmx2048M` parameter in the Java command line to confirm the 2GB heap limit.

## Performance Impact

The reduced memory allocation has minimal impact on build performance for typical Kotlin Multiplatform projects. The 2GB heap is sufficient for:

- Compilation of multiple targets (JVM, JS, Native)
- Dependency resolution
- Compose Multiplatform builds
- Test execution

## Alternative Configurations

For different memory environments:

### 4GB Systems
```properties
kotlin.daemon.jvmargs=-Xmx1024M
org.gradle.jvmargs=-Xmx1536M -Dfile.encoding=UTF-8
```

### 16GB+ Systems
```properties
kotlin.daemon.jvmargs=-Xmx2048M
org.gradle.jvmargs=-Xmx4096M -Dfile.encoding=UTF-8
```

## Troubleshooting

If builds still fail with memory issues:

1. **Check available memory:** `free -m`
2. **Monitor daemon uptime:** `./gradlew --status`
3. **Review daemon logs:** `~/.gradle/daemon/*/daemon-*.out.log`
4. **Further reduce memory if needed**

## References

- [Gradle Daemon Configuration](https://docs.gradle.org/current/userguide/gradle_daemon.html)
- [Kotlin Compiler Daemon](https://kotlinlang.org/docs/gradle-configure-project.html#gradle-daemon-arguments)