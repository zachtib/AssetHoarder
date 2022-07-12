group = "com.zachtib"
version = "1.0-SNAPSHOT"

tasks.create<Delete>("clean") {
    delete(rootProject.buildDir)
}