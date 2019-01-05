// Virtual Machine Java 2015, V01
// Edgar F.A. Lederer, FHNW and Uni Basel, 2015

package ch.fhnw.cpib.project.km.vm;

import ch.fhnw.cpib.project.km.vm.IVirtualMachine.ExecutionError;

public class Data {
	static interface IBaseData {
		IBaseData copy();
	}

	static class IntData implements IBaseData {
		private int i;

		IntData(int i) {
			this.i = i;
		}

		int getData() {
			return i;
		}

		public IntData copy() {
			return intCopy(this);
		}
	}

	static class Int64Data implements IBaseData {
		private long i;

		Int64Data(long i) {
			this.i = i;
		}

		long getData() {
			return i;
		}

		public Int64Data copy() {
			return int64Copy(this);
		}
	}

	static IntData intNew(int i) {
		return new IntData(i);
	}

	static Int64Data int64New(long i) {
		return new Int64Data(i);
	}

	static int intGet(IBaseData a) {
		return ((IntData) a).getData();
	}

	static long int64Get(IBaseData a) {
		return ((Int64Data) a).getData();
	}

	static IntData intCopy(IBaseData a) {
		return intNew(intGet(a));
	}

	static Int64Data int64Copy(IBaseData a) {
		return int64New(int64Get(a));
	}

	// coding booleans as integers
	static IntData boolNew(boolean b) {
		return intNew(b ? 1 : 0);
	}

	// coding booleans as integers
	static boolean boolGet(IBaseData a) {
		return ((IntData) a).getData() != 0;
	}

	static class FloatData implements IBaseData {
		private float f;

		FloatData(float f) {
			this.f = f;
		}

		float getData() {
			return f;
		}

		public FloatData copy() {
			return floatCopy(this);
		}
	}

	static FloatData floatNew(float f) {
		return new FloatData(f);
	}

	static float floatGet(IBaseData a) {
		return ((FloatData) a).getData();
	}

	static FloatData floatCopy(IBaseData a) {
		return floatNew(floatGet(a));
	}

	static IntData intInv(IBaseData a) {
		return intNew(-intGet(a));
	}

	static Int64Data int64Inv(IBaseData a) {
		return int64New(-int64Get(a));
	}

	static IntData boolInv(IBaseData a) {
		return intNew(boolGet(a) ? 0 : 1);
	}

	static FloatData floatInv(IBaseData a) {
		return floatNew(-floatGet(a));
	}

	static IntData intAdd(IBaseData a, IBaseData b) {
		return intNew(intGet(a) + intGet(b));
	}

	static Int64Data int64Add(IBaseData a, IBaseData b) {
		return int64New(int64Get(a) + int64Get(b));
	}

	static IntData intSub(IBaseData a, IBaseData b) {
		return intNew(intGet(a) - intGet(b));
	}

	static Int64Data int64Sub(IBaseData a, IBaseData b) {
		return int64New(int64Get(a) - int64Get(b));
	}

	static IntData intMult(IBaseData a, IBaseData b) {
		return intNew(intGet(a) * intGet(b));
	}

	static Int64Data int64Mult(IBaseData a, IBaseData b) {
		return int64New(int64Get(a) * int64Get(b));
	}

	static IntData intDivTrunc(IBaseData a, IBaseData b) throws IVirtualMachine.ExecutionError {
		try {
			return intNew(intGet(a) / intGet(b));
		} catch (ArithmeticException e) {
			throw new VirtualMachine.ExecutionError("Integer division by zero.");
		}
	}

	static Int64Data int64DivTrunc(IBaseData a, IBaseData b) throws IVirtualMachine.ExecutionError {
		try {
			return int64New(int64Get(a) / int64Get(b));
		} catch (ArithmeticException e) {
			throw new VirtualMachine.ExecutionError("Integer division by zero.");
		}
	}

	static IntData intModTrunc(IBaseData a, IBaseData b) throws IVirtualMachine.ExecutionError {
		try {
			return intNew(intGet(a) % intGet(b));
		} catch (ArithmeticException e) {
			throw new VirtualMachine.ExecutionError("Integer remainder by zero.");
		}
	}

	static Int64Data int64ModTrunc(IBaseData a, IBaseData b) throws IVirtualMachine.ExecutionError {
		try {
			return int64New(int64Get(a) % int64Get(b));
		} catch (ArithmeticException e) {
			throw new VirtualMachine.ExecutionError("Integer remainder by zero.");
		}
	}

	static IntData intEQ(IBaseData a, IBaseData b) {
		return boolNew(intGet(a) == intGet(b));
	}

	static IntData int64EQ(IBaseData a, IBaseData b) {
		return boolNew(int64Get(a) == int64Get(b));
	}

	static IntData intNE(IBaseData a, IBaseData b) {
		return boolNew(intGet(a) != intGet(b));
	}

	static IntData int64NE(IBaseData a, IBaseData b) {
		return boolNew(int64Get(a) != int64Get(b));
	}

	static IntData intGT(IBaseData a, IBaseData b) {
		return boolNew(intGet(a) > intGet(b));
	}

	static IntData int64GT(IBaseData a, IBaseData b) {
		return boolNew(int64Get(a) > int64Get(b));
	}

	static IntData intLT(IBaseData a, IBaseData b) {
		return boolNew(intGet(a) < intGet(b));
	}

	static IntData int64LT(IBaseData a, IBaseData b) {
		return boolNew(int64Get(a) < int64Get(b));
	}

	static IntData intGE(IBaseData a, IBaseData b) {
		return boolNew(intGet(a) >= intGet(b));
	}

	static IntData int64GE(IBaseData a, IBaseData b) {
		return boolNew(int64Get(a) >= int64Get(b));
	}

	static IntData intLE(IBaseData a, IBaseData b) {
		return boolNew(intGet(a) <= intGet(b));
	}

	static IntData int64LE(IBaseData a, IBaseData b) {
		return boolNew(int64Get(a) <= int64Get(b));
	}

	// casts

	public static Int64Data promoteInt32ToInt64(IBaseData iBaseData) {
		return int64New(intGet(iBaseData));
	}

	public static IntData castInt64ToInt32Clamp(IBaseData iBaseData) {
		long value = int64Get(iBaseData);
		value = Math.min(value, Integer.MAX_VALUE);
		value = Math.max(value, Integer.MIN_VALUE);
		return intNew((int) value);
	}

	public static IntData castInt64ToInt32Cut(IBaseData iBaseData) {
		long value = int64Get(iBaseData);
		value &= 0xFFFFFFFF;
		return intNew((int) value);
	}

	public static IntData castInt64ToInt32Lossless(IBaseData iBaseData) throws ExecutionError {
		long value = int64Get(iBaseData);
		int newValue = (int)(value & 0xFFFFFFFF);
		if (value != newValue) {
			// error as lossless was expected
			throw new VirtualMachine.ExecutionError("Lossless Integer cast that caused loss.");
		}
		return intNew((int) value);
	}
}
