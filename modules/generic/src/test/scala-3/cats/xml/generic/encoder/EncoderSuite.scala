package cats.xml.generic.encoder

import cats.xml.XmlNode
import cats.xml.codec.Encoder
import cats.xml.generic.{XmlElemType, XmlTypeInterpreter}
import cats.xml.utils.generic.TypeInfo

class EncoderSuite extends munit.FunSuite {

  import cats.xml.syntax.*
  import cats.xml.generic.Samples.*

//  test("auto") {
//
//    import cats.xml.generic.encoder.auto.*
//
//    assertEquals(
//      obtained = Foo(
//        primitiveField = 1d,
//        valueClass     = ValueClass("TEST"),
//        bar            = Bar("BHO", BigDecimal(100)),
//        missingField   = None,
//        missingNode    = None
//      ).toXml,
//      expected = XmlNode("Foo")
//        .withAttrs(
//          "primitiveField" := 1d,
//          "valueClass"     := "TEST"
//        )
//        .withChildren(
//          XmlNode("Bar")
//            .withAttrs(
//              "field1" := "BHO",
//              "field2" := BigDecimal(100)
//            )
//        )
//    )
//  }

  test("semiauto") {

    import cats.xml.generic.encoder.semiauto.*

//    println(s"The type info is ${TypeInfo[Foo]}")
    implicit val typeInterpreterValueClass: XmlTypeInterpreter[ValueClass] =
      XmlTypeInterpreter.default[ValueClass]
    implicit val typeInterpreterBar: XmlTypeInterpreter[Bar] = XmlTypeInterpreter.default[Bar]
    implicit val typeInterpreterFoo: XmlTypeInterpreter[Foo] =
      XmlTypeInterpreter
        .default[Foo]
        .overrideType(
          _.param(_.valueClass) -> XmlElemType.Attribute
        )

    implicit val encoderValueClass: Encoder[ValueClass] = deriveEncoder[ValueClass]
    implicit val encoderBar: Encoder[Bar]               = deriveEncoder[Bar]
    implicit val encoderFoo: Encoder[Foo]               = deriveEncoder[Foo]
    val xml = Foo(
      primitiveField = 1d,
      valueClass     = ValueClass("TEST"),
      bar            = Bar("BHO", BigDecimal(100)),
      missingField   = None,
      missingNode    = None
    ).toXml
    assertEquals(
      obtained = xml,
      expected = XmlNode("Foo")
        .withAttrs(
          "primitiveField" := 1d,
          "valueClass"     := "TEST"
        )
        .withChildren(
          XmlNode("Bar")
            .withAttrs(
              "field1" := "BHO",
              "field2" := BigDecimal(100)
            )
        )
    )
  }

}
