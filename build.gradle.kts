import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.google.protobuf.gradle.*



plugins {
    id("org.springframework.boot") version "2.7.1"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.7.20"
    kotlin("plugin.serialization") version "1.7.20"//"1.9.21"
    kotlin("plugin.spring") version "1.7.20"
    id("com.google.protobuf") version "0.9.4"
//    id("com.google.protobuf") version "0.8.19"
}


group = "com.example"
version = "0.0.1-SNAPSHOT"



java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

val googleProtobufVersion = "3.19.3"
val ioGrpcVersion = "1.53.0"

val protobufVersion = "3.10.0"
val grpcVersion = "1.60.1"
val grpcKotlinVersionIoRouz = "0.1.4"
//val grpcKotlinVersion = "0.1.4"
val grpcKotlinVersion = "1.4.1"
val kotlinVersion = "1.7.20"
val kotlinCoroutinesVersion = "1.6.3"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
//    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation( "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    implementation("com.google.protobuf:protobuf-kotlin:$googleProtobufVersion")
    implementation("io.grpc:grpc-kotlin-stub:1.3.0")
//    implementation("io.grpc:grpc-stub:$ioGrpcVersion")
//    implementation("io.grpc:grpc-protobuf:$ioGrpcVersion")
    implementation("io.grpc:protoc-gen-grpc-kotlin:1.3.0")

    implementation("net.devh:grpc-server-spring-boot-starter:2.15.0.RELEASE")
    implementation("net.devh:grpc-client-spring-boot-starter:2.15.0.RELEASE")


    // Extra proto source files besides the ones residing under
    // "src/main".
//    protobuf(files("lib/protos.tar.gz"))
    protobuf(files("src/main/resources/proto/"))
    // Extra proto source files for test besides the ones residing under
    // "src/test".
//    testProtobuf(files("lib/protos-test.tar.gz"))
}

protobuf {
    protoc {
        // The artifact spec for the Protobuf Compiler
        artifact = "com.google.protobuf:protoc:${protobufVersion}"
    }
    plugins {

        // Optional: an artifact spec for a protoc plugin, with "grpc" as
        // the identifier, which can be referred to in the "plugins"
        // container of the "generateProtoTasks" closure.
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${grpcVersion}"
        }
//        id("grpckotlin") {
//            artifact = "io.rouz:grpc-kotlin-gen:${grpcKotlinVersionIoRouz}"
//        }
        id("grpckotlin") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:${grpcKotlinVersion}:jdk8@jar"
        }

    }
    generateProtoTasks {
//        all().forEach {
//            it.builtins {
////                id("grpc")
//                id("kotlin")
////                id("grpckt")
//            }
//        }
        ofSourceSet("main").forEach {
            it.plugins {
                // Apply the "grpc" plugin whose spec is defined above, without
                // options. Note the braces cannot be omitted, otherwise the
                // plugin will not be added. This is because of the implicit way
                // NamedDomainObjectContainer binds the methods.
                id("grpc") { }
                id("grpckotlin"){}
            }
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

java {
    val kotlinSrcDir = "src/main/kotlin"
    val asdf = "build/generated/source/proto/main/grpc"
    val asdf2 = "build/generated/source/proto/main/java"
    val asdf3 = "build/generated/source/proto/main/kotlin"
    val asdf4 = "build/generated/source/proto/main/grpckotlin"
    val mainJavaSourceSet: SourceDirectorySet = sourceSets.getByName("main").java
    mainJavaSourceSet.srcDirs(kotlinSrcDir, asdf, asdf2, asdf3, asdf4)
    println("java srcDirs -> "+mainJavaSourceSet.srcDirs)
}
kotlin{
    val kotlinSrcDir = "src/main/kotlin"
    val asdf = "build/generated/source/proto/main/grpc"
    val asdf2 = "build/generated/source/proto/main/java"
    val asdf3 = "build/generated/source/proto/main/kotlin"
    val asdf4 = "build/generated/source/proto/main/grpckotlin"
    val mainJavaSourceSet: SourceDirectorySet = sourceSets.getByName("main").kotlin
    mainJavaSourceSet.srcDirs(kotlinSrcDir, asdf, asdf2, asdf3, asdf4)
    println("kotlin srcDirs -> "+mainJavaSourceSet.srcDirs)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

//protobuf {
//    protoc {
//        artifact = "com.google.protobuf:protoc:3.0.0"
//    }
//}
