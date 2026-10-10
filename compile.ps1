$src = Get-ChildItem -Recurse -Filter "*.java" "src/main/java" | ForEach-Object { $_.FullName }
javac --release 24 -encoding UTF-8 -cp "lib/junit-platform-console-standalone-1.10.3.jar" -d target/classes $src
