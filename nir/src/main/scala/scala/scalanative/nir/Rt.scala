package scala.scalanative
package nir

import Type._

object Rt {
  val RtName      = Global.Val("scala.scalanative.runtime.package")
  val Rt          = Module(RtName)
  val String      = Class(Global.Type("java.lang.String"))
  val Object      = Class(Global.Type("java.lang.Object"))
  val BoxedUnit   = Class(Global.Type("scala.runtime.BoxedUnit"))
  val ObjectArray = Class(Global.Type("scala.scalanative.runtime.ObjectArray"))
  val Type = Struct(
      Global.Type("scala.scalanative.runtime.Type"), Seq(I32, Ptr))
  val Exc = Struct(Global.Type("scala.scalanative.runtime.Exc"), Seq(Ptr, I32))

  val unitName  = Global.Val("scala.scalanative.runtime.BoxedUnit")
  val unit      = Val.Global(unitName, Ptr)
  val unitTy    = Struct(BoxedUnit.name tag "class", Seq(Ptr))
  val unitConst = Val.Global(BoxedUnit.name tag "const", Ptr)
  val unitValue = Val.Struct(unitTy.name, Seq(unitConst))
  val unitDefn  = Defn.Const(Attrs.None, unitName, unitTy, unitValue)

  val mainName = Global.Val("main")
  val mainSig  = Function(Seq(I32, Ptr), I32)

  val initName = RtName member "init_i32_ptr_class.ssnr.ObjectArray"
  val initSig  = Function(Seq(Rt, I32, Ptr), ObjectArray)
  val init     = Val.Global(initName, initSig)

  val throwName = Global.Val("scalanative_throw")
  val throwSig  = Function(Seq(Ptr), Void)
  val throw_    = Val.Global(throwName, Ptr)
  val throwDefn = Defn.Declare(Attrs.None, throwName, throwSig)

  val beginCatchName = Global.Val("scalanative_begin_catch")
  val beginCatchSig  = Function(Seq(Ptr), Ptr)
  val beginCatch     = Val.Global(beginCatchName, Ptr)
  val beginCatchDefn = Defn.Declare(Attrs.None, beginCatchName, beginCatchSig)

  val endCatchName = Global.Val("scalanative_end_catch")
  val endCatchSig  = Function(Seq(), Void)
  val endCatch     = Val.Global(endCatchName, endCatchSig)
  val endCatchDefn = Defn.Declare(Attrs.None, endCatchName, endCatchSig)

  val allocName = Global.Val("scalanative_alloc")
  val allocSig  = Function(Seq(Ptr, I64), Ptr)
  val alloc     = Val.Global(allocName, allocSig)
  val allocDefn = Defn.Declare(Attrs.None, allocName, allocSig)

  val defns = Seq(
      unitDefn,
      throwDefn,
      beginCatchDefn,
      endCatchDefn,
      allocDefn
  )

  def pinned = Seq(
      Rt.name,
      init.name,
      String.name,
      Object.name,
      BoxedUnit.name,
      ObjectArray.name,
      Type.name,
      Exc.name
  )
}
