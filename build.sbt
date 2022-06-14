lazy val `typicode-laminar` =
  (project in file("."))
    .enablePlugins(ScalaJSPlugin)
    .settings(
      name                            := "typicode-laminar",
      version                         := "0.0.1",
      scalaVersion                    := "3.1.2",
      organization                    := "pro.reiss",
      libraryDependencies ++= Seq(
        "com.raquo" %%% "laminar" % "0.14.2"
      ),
      scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) },
      scalaJSLinkerConfig ~= { _.withSourceMap(false) },
      scalaJSUseMainModuleInitializer := true
    )
