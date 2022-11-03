Tests can be ran with SOONG/atest or Gradle:

SOONG:
mmma -j64 packages/apps/Car/Media/
atest CarMediaAppTests

Gradle:
cd ../libs/aaos-apps-gradle-project
./gradlew  :car-media-app:test