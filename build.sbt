lazy val `typicode-laminar` =
  (project in file("."))
    .enablePlugins(ScalaJSPlugin)
    .settings(
      name                            := "typicode-laminar",
      version                         := "0.1.0",
      scalaVersion                    := "3.1.2",
      organization                    := "pro.reiss",
      libraryDependencies ++= Seq(
        "com.raquo"                   %%% "laminar"           % "0.14.2",
        "com.softwaremill.sttp.tapir" %%% "tapir-sttp-client" % "1.0.0",
        "com.softwaremill.sttp.tapir" %%% "tapir-json-circe"  % "1.0.0",
        "io.github.cquiroz"           %%% "scala-java-time"   % "2.4.0" // implementations of java.time classes for Scala.JS
      ),
      scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) },
      scalaJSLinkerConfig ~= { _.withSourceMap(false) },
      scalaJSUseMainModuleInitializer := true
    )
