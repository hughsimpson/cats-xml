import sbt.{CrossVersion, _}

/** cats-xml Created by geirolz on 30/07/2019.
  *
  * @author
  *   geirolz
  */
object ProjectDependencies {

  lazy val common: Seq[ModuleID] = Seq(
    // SCALA
    "org.typelevel" %% "cats-core" % "2.13.0",
    // TEST
    "org.scalameta"  %% "munit"            % "1.1.0"  % Test,
    "org.scalameta"  %% "munit-scalacheck" % "1.1.0"  % Test,
    "org.typelevel"  %% "cats-laws"        % "2.13.0" % Test,
    "org.typelevel"  %% "discipline-munit" % "2.0.0"  % Test,
    "org.scalacheck" %% "scalacheck"       % "1.18.1" % Test
  )

  object Docs {
    val dedicated: Seq[ModuleID] = Nil
  }

  object Utils {
    val dedicated: Seq[ModuleID] = List(
      "org.scala-lang" % "scala-reflect" % "2.13.16"
    )
  }

  object Metrics {
    val dedicated: Seq[ModuleID] = Nil
  }

  object Generic {
    val scala2: Seq[ModuleID] = Seq(
      "com.softwaremill.magnolia1_2" %% "magnolia"      % "1.1.10",
      "org.scala-lang"                % "scala-reflect" % "2.13.16",
      "com.chuusai"                  %% "shapeless"     % "2.3.13"
    )
    val scala3: Seq[ModuleID] = Seq(
      "com.softwaremill.magnolia1_3" %% "magnolia" % "1.3.16"
    )
  }

  object Effect {
    val dedicated: Seq[ModuleID] = Seq(
      "org.typelevel" %% "cats-effect"       % "3.5.7",
      "org.typelevel" %% "munit-cats-effect" % "2.0.0" % Test
    )
  }

  object Standard {
    val dedicated: Seq[ModuleID] = Seq(
      "org.scala-lang.modules" %% "scala-xml" % "2.3.0"
    )
  }

  object Xpath {
    val dedicated: Seq[ModuleID] = Seq(
      "eu.cdevreeze.xpathparser" %% "xpathparser" % "0.8.0"
    )
  }

  object Plugins {
    lazy val compilerPluginsFor2_13: Seq[ModuleID] = Seq(
      compilerPlugin("org.typelevel" %% "kind-projector" % "0.13.3" cross CrossVersion.full)
    )
    lazy val compilerPluginsFor3: Seq[ModuleID] = Nil
  }
}
