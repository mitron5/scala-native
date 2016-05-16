package scala.scalanative
package compiler

import Target._

/** Structured representation of LLVM's target triple. */
final case class Target(arch: Arch, vendor: Vendor, os: OS)

object Target {
  val current = Target(Arch.x86_64, Vendor.Apple, OS.MacOSX("10.11.0"))

  sealed abstract class Arch { def word: nir.Type }
  object Arch {
    final case object x86    extends Arch { def word: nir.Type = nir.Type.I32 }
    final case object x86_64 extends Arch { def word: nir.Type = nir.Type.I64 }
  }

  sealed abstract class Vendor
  object Vendor {
    final case object Apple extends Vendor
  }

  sealed abstract class OS
  object OS {
    final case class MacOSX(version: String) extends OS
  }
}
