package ch.fhnw.cpib.project.km.code;

import java.util.ArrayList;
import java.util.List;

public class IMLTestPrograms {
	
	public static class IMLProgram {
		public IMLProgram(String title, String code) {
			super();
			this.title = title;
			this.code = code;
		}
		public final String title;
		public final String code;
	}
	
	// invalid
	public static IMLProgram DearGrandmother = new IMLProgram("DearGrandmother","Liebe Grossmutter ;)\r\n" + "Zu Deinem 67-ten Geburtstag\n");

	// IML Example Program (Basic IML)
	// from cpib_HS-2018_Slides_IML_V1.pdf page 5
	public static IMLProgram IntDiv64 = new IMLProgram("IntDiv64","program intDiv64(in const m:int64, in const n:int64,\r\n"
			+ "               out const q:int64, out const r:int64)\r\n" + "global\r\n"
			+ "  proc divide(in copy const m:int64, in copy const n:int64,\r\n"
			+ "              out ref var q:int64, out ref var r:int64)\r\n" + "  do\r\n" + "    q init := 0;\r\n"
			+ "    r init := m;\r\n" + "    while r >= n do\r\n" + "      q := q + 1;\r\n" + "      r := r - n\r\n"
			+ "    endwhile\r\n" + "  endproc\r\n" + "do\r\n" + "  call divide(m, n, q init, r init)\r\n"
			+ "endprogram\r\n" + "");

	// from IML_ExamplePrograms/
	public static IMLProgram IntDiv = new IMLProgram("IntDiv","program intDiv(in  m:int32, in  n:int32,\r\n"
			+ "               out q:int32, out r:int32)\r\n" + "global\r\n"
			+ "  proc divide(in copy const m:int32, in copy const n:int32,\r\n"
			+ "              out ref var   q:int32, out ref var   r:int32)\r\n" + "  do\r\n" + "    q init := 0;\r\n"
			+ "    r init := m;\r\n" + "    while r >= n do\r\n" + "      q := q + 1 ;\r\n" + "      r := r - n\r\n"
			+ "    endwhile\r\n" + "  endproc\r\n" + "do\r\n" + "  call divide(m, n, q init, r init)\r\n"
			+ "endprogram\r\n");

	// from IML_ExamplePrograms/
	public static IMLProgram Assoc = new IMLProgram("Assoc","program Assoc()\r\n" + "global x:int32\r\n" + "do\r\n" + "  debugin x init ;\r\n"
			+ "\r\n" + "  debugout x - (x - x) ;\r\n" + "  debugout (x - x) - x ;\r\n" + "  debugout x - x - x\r\n"
			+ "endprogram\n");

	// from IML_ExamplePrograms/
	public static IMLProgram GlobalInits1 = new IMLProgram("GlobalInits1","program GlobalInits1()\r\n" + "global\r\n" + "var g1:int64;\r\n"
			+ "var g2:int64;\r\n" + "proc p(in copy const x:int64)\r\n" + "global out const g1, out const g2\r\n"
			+ "do\r\n" + "g1 init := x * x;\r\n" + "g2 init := -g1\r\n" + "endproc\r\n" + "do\r\n"
			+ "call p(5) init g1, g2;" + "debugout g1;" + "debugout g2;\r\n" + "call p(6);" + "debugout g1;"
			+ "debugout g2\r\n" + "endprogram\r\n");

	// from IML_ExamplePrograms/
	public static IMLProgram GlobalInits2 = new IMLProgram("GlobalInits2","// BasicIML V01\r\n" + "// Edgar F.A. Lederer, FHNW, October 2018\r\n" + "\r\n"
			+ "program GlobalInits2()\r\n" + "global\r\n" + "  var g1:int32 ;\r\n" + "  var g2:int32 ;\r\n" + "\r\n"
			+ "  proc p(in copy const x:int32)\r\n" + "    global out const g1, out const g2\r\n" + "  do\r\n"
			+ "    g1 init := x * x ;\r\n" + "    g2 init := -g1\r\n" + "  endproc ;\r\n" + "\r\n"
			+ "  proc q(in copy const x:int32)\r\n" + "    global out var g1, out var g2\r\n" + "  do\r\n"
			+ "    call p(x) init g1, g2 ;\r\n" + "    call p(x+1)\r\n" + "  endproc\r\n" + "do\r\n"
			+ "  call q(5) init g1, g2 ;   debugout g1 ; debugout g2 ;\r\n"
			+ "  call q(6) ;               debugout g1 ; debugout g2\r\n" + "endprogram\r\n" + "\r\n"
			+ "// The same as GloablInits1, but with an additional intermediate procedure q.\r\n");

	// from IML_ExamplePrograms/
	public static IMLProgram GlobalInits3 = new IMLProgram("GlobalInits3","// BasicIML V01\r\n" + "// Edgar F.A. Lederer, FHNW, October 2018\r\n" + "\r\n"
			+ "program GlobalInits3()\r\n" + "global\r\n" + "  var g1:int32 ;\r\n" + "  var g2:int32 ;\r\n" + "\r\n"
			+ "  proc p(in copy const x:int32, in copy const n:int32)\r\n" + "    global out const g1, out const g2\r\n"
			+ "  do\r\n" + "    if n = 0 then\r\n" + "      g1 init := x * x ;\r\n" + "      g2 init := -g1\r\n"
			+ "    else\r\n" + "      call p(x+1, n-1) init g1, g2\r\n" + "    endif\r\n" + "  endproc\r\n" + "do\r\n"
			+ "  call p(0, 5) init g1, g2 ;   debugout g1 ; debugout g2 ;\r\n"
			+ "  call p(1, 5) ;               debugout g1 ; debugout g2\r\n" + "endprogram\r\n" + "\r\n"
			+ "// The same as GloablInits1, but in a recursive setting.\r\n");

	// from IML_ExamplePrograms/
	public static IMLProgram Globals = new IMLProgram("Globals","// BasicIML V01\r\n" + "// Edgar F.A. Lederer, FHNW, October 2018\r\n" + "\r\n"
			+ "program Globals()\r\n" + "  global const g1:int32 ; var g2:int32 ; const g3:int32 ;\r\n" + "\r\n"
			+ "  proc p()\r\n" + "    global in const g1, inout var g2, out var g3\r\n"
			+ "    // The following does not work:\r\n" + "    // global in const g1, out var g3\r\n" + "  do\r\n"
			+ "    // Here, the global stores g1 and g2 are accessed, but invisible\r\n"
			+ "    //   (g3 IS visible).\r\n" + "    // However, they must be declared as global imports.\r\n"
			+ "    // So the whole communications interface becomes visible\r\n"
			+ "    //   (in the header of the procedure).\r\n" + "    call q() init g3 ;\r\n" + "    call q()\r\n"
			+ "  endproc ;\r\n" + "\r\n" + "  proc q()\r\n" + "    global in const g1, inout var g2, out const g3\r\n"
			+ "  do\r\n" + "    // g1 is in and thus already initialized; here it is read and not updated\r\n"
			+ "    // g2 is inout and thus already initialized;\r\n"
			+ "    //   here it is read and then updated and then read again\r\n"
			+ "    // g3 is out and thus not yet initialized; here it is initialized\r\n" + "    g2 := g2 + g1 ;\r\n"
			+ "    g3 init := g2\r\n" + "  endproc\r\n" + "do\r\n" + "  g1 init := 3 ;\r\n" + "  g2 init := 5 ;\r\n"
			+ "  call p() init g3 ;\r\n" + "  debugout g1 ; debugout g2 ; debugout g3\r\n" + "endprogram\r\n");

	// from IML_ExamplePrograms/
	public static IMLProgram ParameterPassingInCopy = new IMLProgram("ParameterPassingInCopy","// BasicIML V01\r\n"
			+ "// Edgar F.A. Lederer, FHNW, October 2018\r\n" + "\r\n" + "program ParameterPassingInCopy()\r\n"
			+ "global\r\n" + "  var y:int32 ; const a:int32 ; const b:int32 ;\r\n" + "\r\n"
			+ "  proc p1(in copy const x:int32, out copy y:int32)\r\n" + "  do\r\n"
			+ "    // x is accessible, but reading it is not enforced\r\n" + "    y init := 0\r\n" + "  endproc ;\r\n"
			+ "\r\n" + "  proc p2(in copy const x:int32, out copy y:int32)\r\n" + "  do\r\n"
			+ "    y init := x // x is read\r\n" + "    //; x := x + 1 // illegal update access of constant\r\n"
			+ "  endproc ;\r\n" + "\r\n" + "  proc p3(in copy var x:int32, out copy y:int32)\r\n" + "  do\r\n"
			+ "    x := x + 1 // x is read and then updated\r\n" + "    ; y init := x\r\n" + "  endproc\r\n" + "do\r\n"
			+ "  a init := 5 ; b init := 7 ; y init := 0 ;\r\n" + "  // calls with rExpr\r\n"
			+ "  call p1(a + b, y) ; debugout y ;\r\n" + "  call p2(a + b, y) ; debugout y ;\r\n"
			+ "  call p3(9, y) ; debugout y\r\n" + "endprogram\r\n");

	// from IML_ExamplePrograms/
	public static IMLProgram ParameterPassingInRef = new IMLProgram("ParameterPassingInRef","// BasicIML V01\r\n" + "// Edgar F.A. Lederer, FHNW, October 2018\r\n"
			+ "\r\n" + "program ParameterPassingInRef()\r\n" + "global\r\n"
			+ "  var y:int32 ; const a:int32 ; const b:int32 ;\r\n" + "\r\n"
			+ "  proc p1(in ref const x:int32, out copy y:int32)\r\n" + "  do\r\n"
			+ "    // x is accessible, but reading it is not enforced\r\n" + "    y init := 0\r\n" + "  endproc ;\r\n"
			+ "\r\n" + "  proc p2(in ref const x:int32, out copy y:int32)\r\n" + "  do\r\n"
			+ "    y init := x // x is read\r\n" + "    //; x := x + 1 // illegal update access of constant\r\n"
			+ "  endproc\r\n" + "\r\n" + "  // change mode must be constant\r\n"
			+ "  //proc p3(in ref var x:int32, out copy y:int32)\r\n" + "  //do\r\n" + "    //skip\r\n"
			+ "  //endproc\r\n" + "do\r\n" + "  a init := 5 ; b init := 7 ; y init := 0 ;\r\n"
			+ "  // expression must be an lExpr\r\n" + "  //call p1(a + b, y) ; debugout y ;\r\n"
			+ "  call p1(a, y) ; debugout y ;\r\n" + "  call p2(a, y) ; debugout y\r\n" + "endprogram\r\n");

	// from IML_ExamplePrograms/
	public static IMLProgram EuclidExtendedNat = new IMLProgram("EuclidExtendedNat","// BasicIML V01\r\n" + "// Edgar F.A. Lederer, FHNW, 2017\r\n" + "\r\n"
			+ "program EuclidExtendedNat\r\n" + "  (in a:int64, in b:int64,\r\n"
			+ "   out g:int64, out u:int64, out v:int64, out u':int64, out v':int64,\r\n"
			+ "   out sign:int32, out numIt:int32)\r\n" + "global\r\n" + "  proc euclidExtendedNatWOSign\r\n"
			+ "    (in copy const a:int64, in copy const b:int64,\r\n" + "     out copy var g :int64,\r\n"
			+ "     out copy var u :int64, out copy var v :int64,\r\n"
			+ "     out copy var u':int64, out copy var v':int64,\r\n"
			+ "     out copy var sign:int32, out var numIt:int32)\r\n" + "    //pre 0 <= a &? 0 <= b\r\n"
			+ "    //post 0 <= g = gcd(a, b)\r\n" + "    //post |sign| = 1\r\n"
			+ "    //Pu':   post -sign * u' * g = b\r\n" + "    //Pv':   post +sign * v' * g = a\r\n"
			+ "    //Puv:   post u  * a + v  * b = g\r\n" + "    //Pu'v': post u' * a + v' * b = 0\r\n"
			+ "    //Psign: post u * v' - u' * v = sign\r\n" + "  local\r\n" + "    var g':int64 ; var q:int64\r\n"
			+ "  do\r\n" + "    g init := a ; g' init := b ;\r\n" + "    u init := 1 ; u' init := 0 ;\r\n"
			+ "    v init := 0 ; v' init := 1 ;\r\n" + "    sign init := 1;\r\n" + "    numIt init:= 0;\r\n"
			+ "    q init := 0 ; // local\r\n" + "    while g' > 0 do\r\n" + "      q := g divE g' ;\r\n"
			+ "      g := g - q * g'  ; u := u - q * u'  ; v := v - q * v' ;\r\n"
			+ "      call swap(g, g') ; call swap(u, u') ; call swap(v, v') ;\r\n" + "      sign := -sign ;\r\n"
			+ "      numIt := numIt + 1\r\n" + "    endwhile\r\n" + "  endproc ;\r\n" + "\r\n"
			+ "  proc swap(inout ref var x:int64, inout ref var y:int64)\r\n" + "  local const h:int64\r\n"
			+ "  do h init := x ; x := y ; y := h endproc\r\n" + "do\r\n" + "  call euclidExtendedNatWOSign\r\n"
			+ "    (a, b, g init, u init, v init, u' init, v' init, sign init, numIt init)\r\n" + "endprogram\r\n");

	// from StaticAnalysis Page 7
	public static IMLProgram Scopes = new IMLProgram("Scopes","// from StaticAnalysis Page 7\r\n" + "\r\n" + "program Scopes()\r\n" + "global\r\n"
			+ "    fun f(p1:int32) returns r1:int32 global g1\r\n" + "    local\r\n"
			+ "        var l1:int32; g3:int32\r\n" + "    do\r\n" + "        debugout(g1); // global\r\n"
			+ "        //debugout(g2); // global, but not imported\r\n"
			+ "        g3 init := -3; // local (also global, but not imported)\r\n" + "        debugout(g3);\r\n"
			+ "        debugout(p1); // local\r\n" + "        l1 init := -1; // local\r\n" + "        debugout(l1);\r\n"
			+ "        if p1 > 0 then\r\n" + "            r1 init := p1 * f(p1 - 1)\r\n" + "        else\r\n"
			+ "            r1 init := 1\r\n" + "        endif;\r\n" + "        debugout(r1)\r\n" + "    endfun ;\r\n"
			+ "    g1:int32 ;\r\n" + "    g2:int32 ;\r\n" + "    g3:int32 ;\r\n"
			+ "    f: int32 // two namespaces: one for stores, one for routines\r\n" + "do\r\n"
			+ "    g1 init := 1;\r\n" + "    debugin(g2 init);\r\n" + "    g3 init := 3;\r\n" + "    f init := 0;\r\n"
			+ "    debugout(f(g2))\r\n" + "endprogram\r\n");

	// Bericht
	public static IMLProgram Overloading = new IMLProgram("Overloading","program testOverloading(in a:int32, in b:int64)\r\n" + 
			"global\r\n" + 
			" proc printSum(in copy const m:int32, in copy const n:int32)\r\n" + 
			" local\r\n" + 
			"   var s:int32\r\n" + 
			" do\r\n" + 
			"   s init := m + n;\r\n" + 
			"   debugout s\r\n" + 
			" endproc ;\r\n" + 
			"\r\n" + 
			" proc printSum(in copy const m:int32, in copy const n:int64)\r\n" + 
			" local\r\n" + 
			"   var s:int64\r\n" + 
			" do\r\n" + 
			"   s init := m + n;\r\n" + 
			"   debugout s\r\n" + 
			" endproc ;\r\n" + 
			"\r\n" + 
			" proc printSum(in copy const m:int64, in copy const n:int64)\r\n" + 
			" local\r\n" + 
			"   var s:int64\r\n" + 
			" do\r\n" + 
			"   s init := m + n;\r\n" + 
			"   debugout s\r\n" + 
			" endproc ;\r\n" + 
			"\r\n" + 
			"proc printSum(in copy const m:int64, in copy const n:int32)\r\n" + 
			" local\r\n" + 
			"   var s:int64\r\n" + 
			" do\r\n" + 
			"   s init := m + n;\r\n" + 
			"   debugout s\r\n" + 
			" endproc\r\n" + 
			"\r\n" + 
			"do\r\n" + 
			" call printSum(a, a); // calls  printSum(int32,int32)\r\n" + 
			" call printSum(b, b); // calls  printSum(int64,int64)\r\n" + 
			" call printSum(a, b); // calls  printSum(int32,int64)\r\n" + 
			" call printSum(b, a); // calls  printSum(int64,int32)\r\n" + 
			" //call printSum(b, a, a); // compile time error, no matching overload found\r\n" + 
			" skip\r\n" + 
			"endprogram\r\n" + 
			"");

	public static IMLProgram Overloading2 = new IMLProgram("Overloading2","program testOverloading(in a:int32, in b:int64)\r\n" + 
			"global\r\n" + 
			"\r\n" + 
			" proc printSum(in copy const m:int32, in copy const n:int64)\r\n" + 
			" local\r\n" + 
			"   var s:int64\r\n" + 
			" do\r\n" + 
			"   s init := m + n;\r\n" + 
			"   debugout s\r\n" + 
			" endproc ;\r\n" + 
			"\r\n" + 
			" proc printSum(in copy const m:int64, in copy const n:int64)\r\n" + 
			" local\r\n" + 
			"   var s:int64\r\n" + 
			" do\r\n" + 
			"   s init := m + n;\r\n" + 
			"   debugout s\r\n" + 
			" endproc\r\n" + 
			"do\r\n" + 
			" call printSum(a, b); // calls  printSum(int32,int64)\r\n" + 
			" //call printSum(a, a); // compile time error, multiple possible matches\r\n" + 
			" skip\r\n" + 
			"endprogram\r\n" + 
			"");

	public static IMLProgram Overloading3 = new IMLProgram("Overloading3","program testOverloading(inout a:int32)\r\n" + 
			"global\r\n" + 
			"\r\n" + 
			" proc setNull(out copy m:int64)\r\n" + 
			" do\r\n" + 
			"   m init := 0\r\n" + 
			" endproc\r\n" + 
			"do\r\n" + 
			"   //call setNull(a); // compile time error, no match found\r\n" + 
			"   skip\r\n" +
			"endprogram\r\n" + 
			"");


	public static IMLProgram Overloading4 = new IMLProgram("Overloading4","program testOverloading()\r\n" + 
			"global\r\n" + 
			" fun returnNull(in copy const n:int32) returns const r:int32\r\n" + 
			" do\r\n" + 
			"   r init := 0\r\n" + 
			" endfun ;\r\n" + 
			"\r\n" + 
			" fun returnNull(in copy const n:int64) returns const r:int64\r\n" + 
			" do\r\n" + 
			"   r init := 0\r\n" + 
			" endfun;\r\n" + 
			" const a:int32;\r\n" + 
			" const b:int64\r\n" + 
			" do\r\n" + 
			"   a init := 5;\r\n" + 
			"   b init := 7;\r\n" + 
			"   debugout returnNull(a) ; // calls setNull(int32)\r\n" + 
			"   debugout returnNull(b) // calls setNull(int64)\r\n" + 
			"endprogram\r\n" + 
			"");
	
	// Beispiel Bericht: int64 to int32 clamp
	public static IMLProgram Clamp = new IMLProgram("Cast Clamp","program testToInt32Clamp()\r\n" + "global\r\n" + " var normalInt:int64;\r\n"
			+ " var biggestInt:int64;\r\n" + " var smallestInt:int64;\r\n" + " var bigInt:int64;\r\n"
			+ " var smallInt:int64\r\n" + "do\r\n" + "  normalInt init := 5;\r\n"
			+ "  biggestInt init := 2147483647; // int32.maxValue\r\n"
			+ "  smallestInt init := -2147483648; // int32.minValue\r\n"
			+ "  bigInt init := 10000000000; // value bigger than int32.maxValue\r\n"
			+ "  smallInt init := -10000000000; // value smaller than int32.minValue\r\n"
			+ "  debugout toInt32Clamp(normalInt); // prints 5\r\n"
			+ "  debugout toInt32Clamp(biggestInt); // prints int32.maxValue which is 2,147,483,647\r\n"
			+ "  debugout toInt32Clamp(smallestInt); // prints int32.minValue which is -2,147,483,648\r\n"
			+ "  debugout toInt32Clamp(bigInt); // prints int32.maxValue which is 2,147,483,647\r\n"
			+ "  debugout toInt32Clamp(smallInt) // prints int32.minValue which is -2,147,483,648\r\n"
			+ "endprogram\r\n");

	// Beispiel 3 Bericht
	public static IMLProgram Cut = new IMLProgram("Cast Cut","program testToInt32Cut()\r\n" + "global\r\n" + "  var normalInt:int64;\r\n"
			+ "  var biggestInt:int64;\r\n" + "  var smallestInt:int64;\r\n" + "  var bigInt:int64;\r\n"
			+ "  var smallInt:int64\r\n" + "do\r\n" + "  normalInt init := 5;\r\n"
			+ "  biggestInt init := 2147483647; // int32.maxValue\r\n"
			+ "  smallestInt init := -2147483648; // int32.minValue\r\n"
			+ "  bigInt init := 10000000000; // value bigger than int32.maxValue\r\n"
			+ "  smallInt init := -10000000000; // value smaller than int32.minValue\r\n"
			+ "  debugout toInt32Cut(normalInt); // prints 5\r\n"
			+ "  debugout toInt32Cut(biggestInt); // prints int32.maxValue which is 2,147,483,647\r\n"
			+ "  debugout toInt32Cut(smallestInt); // prints int32.minValue which is -2,147,483,648\r\n"
			+ "  debugout toInt32Cut(bigInt); // prints int32.maxValue which is 2,147,483,647\r\n"
			+ "  debugout toInt32Cut(smallInt) // prints int32.minValue which is -2,147,483,648\r\n" + "endprogram\r\n"
			+ "");

	public static IMLProgram Lossless = new IMLProgram("Cast Lossless","program testToInt32Lossless()\r\n" + "global\r\n" + "  var normalInt:int64;\r\n"
			+ "  var biggestInt:int64;\r\n" + "  var smallestInt:int64;\r\n" + "  var bigInt:int64;\r\n"
			+ "  var smallInt:int64\r\n" + "do\r\n" + "  normalInt init := 5;\r\n"
			+ "  biggestInt init := 2147483647; // int32.maxValue\r\n"
			+ "  smallestInt init := -2147483648; // int32.minValue\r\n"
			+ "  bigInt init := 10000000000; // value bigger than int32.maxValue\r\n"
			+ "  smallInt init := -10000000000; // value smaller than int32.minValue\r\n"
			+ "  debugout toInt32Lossless(normalInt); // prints 5\r\n"
			+ "  debugout toInt32Lossless(biggestInt); // prints int32.maxValue which is 2,147,483,647\r\n"
			+ "  debugout toInt32Lossless(smallestInt); // prints int32.minValue which is -2,147,483,648\r\n"
			+ "  debugout toInt32Lossless(bigInt); // throws ExecutionError \r\n"
			+ "  debugout toInt32Lossless(smallInt)  // throws ExecutionError \r\n" + "endprogram\r\n" + "");

	public static IMLProgram OperationOnInt64 = new IMLProgram("OperationOnInt64","program operationOnInt64()\r\n" + "global\r\n" + " var a:int64;\r\n"
			+ " var b:int64\r\n" + "do\r\n" + " a := 10;\r\n" + " b := 20;\r\n" + " debugout a + b;\r\n"
			+ " debugout a - b;\r\n" + " debugout a * b;\r\n" + " debugout a divE b;\r\n" + " debugout a modE b;\r\n"
			+ " debugout a = b;\r\n" + " debugout a < b;\r\n" + " debugout a > b;\r\n" + " debugout a /= b;\r\n"
			+ " debugout a >= b;\r\n" + " debugout a <= b\r\n" + "endprogram\r\n");
	
	public static IMLProgram OperationOnInt64_1 = new IMLProgram("OperationOnInt64 int32 int64","program operationOnInt64()\r\n" + "global\r\n" + " var a:int32;\r\n"
			+ " var b:int64\r\n" + "do\r\n" + " a := 10;\r\n" + " b := 20;\r\n" + " debugout a + b;\r\n"
			+ " debugout a - b;\r\n" + " debugout a * b;\r\n" + " debugout a divE b;\r\n" + " debugout a modE b;\r\n"
			+ " debugout a = b;\r\n" + " debugout a < b;\r\n" + " debugout a > b;\r\n" + " debugout a /= b;\r\n"
			+ " debugout a >= b;\r\n" + " debugout a <= b\r\n" + "endprogram\r\n");
	
	public static IMLProgram OperationOnInt64_2 = new IMLProgram("OperationOnInt64 int64 int32","program operationOnInt64()\r\n" + "global\r\n" + " var a:int64;\r\n"
			+ " var b:int32\r\n" + "do\r\n" + " a := 10;\r\n" + " b := 20;\r\n" + " debugout a + b;\r\n"
			+ " debugout a - b;\r\n" + " debugout a * b;\r\n" + " debugout a divE b;\r\n" + " debugout a modE b;\r\n"
			+ " debugout a = b;\r\n" + " debugout a < b;\r\n" + " debugout a > b;\r\n" + " debugout a /= b;\r\n"
			+ " debugout a >= b;\r\n" + " debugout a <= b\r\n" + "endprogram\r\n");

	public static IMLProgram Faculty = new IMLProgram("Faculty","program faculty(in n:int32)\r\n" + "global\r\n"
			+ "fun facultyLoop(in copy const n:int32) returns var r:int32\r\n" + "local\r\n" + " var i:int32\r\n"
			+ "do\r\n" + " i init := n-1;\r\n" + " r init := n;\r\n" + " while i>1 do\r\n" + "   r := r * i;\r\n"
			+ "   i := i - 1\r\n" + " endwhile\r\n" + "endfun\r\n;"
			+ " fun facultyRecursiv(in copy const n:int32) returns const r:int32\r\n" + " do\r\n" + "  if n>1 then\r\n"
			+ "   r init := n * facultyRecursiv(n-1)\r\n" + "  else\r\n" + "   r init := 1\r\n" + "  endif\r\n"
			+ " endfun\r\n" + "do\r\n" + " debugout facultyLoop(n);\r\n" + " debugout facultyRecursiv(n)\r\n"
			+ "endprogram\r\n");

	public static List<IMLProgram> getValidPrograms() {
		List<IMLProgram> ret = new ArrayList<>();
		ret.add(IntDiv64);
		ret.add(IntDiv);
		ret.add(Assoc);
		ret.add(GlobalInits1);
		ret.add(GlobalInits2);
		ret.add(GlobalInits3);
		ret.add(Globals);
		ret.add(ParameterPassingInCopy);
		ret.add(ParameterPassingInRef);
		ret.add(EuclidExtendedNat);
		ret.add(Scopes);
		ret.add(Overloading);
		ret.add(Overloading2);
		ret.add(Overloading3);
		ret.add(Overloading4);
		ret.add(Clamp);
		ret.add(Cut);
		ret.add(Lossless);
		ret.add(OperationOnInt64);
		ret.add(OperationOnInt64_1);
		ret.add(OperationOnInt64_2);
		ret.add(Faculty);
		return ret;
	}
}
