// Virtual Machine Java 2015, V01
// Edgar F.A. Lederer, FHNW and Uni Basel, 2015

package ch.fhnw.cpib.project.km.vm;

import ch.fhnw.cpib.project.km.vm.IVirtualMachine.ExecutionError;

// idea: the compiler should not need a reference to the VM object

public interface IInstructions {

	// non-executable form of instructions
	interface IInstr {
		IExecInstr toExecInstr(VirtualMachine vm);
	}

	// executable form of instructions
	interface IExecInstr extends IInstr {
		void execute() throws ExecutionError;
	}

	// stop instruction
	class Stop implements IInstr {
		public String toString() {
			return "Stop";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new StopExec();
		}
	}

	// stack instruction
	class Dup implements IInstr {
		public String toString() {
			return "Dup";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new DupExec();
		}
	}

	// routine operations

	class PopSingleBlock implements IInstr {
		public PopSingleBlock() {
		}

		public String toString() {
			return "PopSingleBlock";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new PopSingleBlockExec();
		}
	}

	class AllocBlock implements IInstr {
		protected int size;

		public AllocBlock(int size) {
			this.size = size;
		}

		public String toString() {
			return "AllocBlock(" + size + ")";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new AllocBlockExec(size);
		}
	}

	class AllocStack implements IInstr {
		protected int maxSize;

		public AllocStack(int maxSize) {
			this.maxSize = maxSize;
		}

		public String toString() {
			return "AllocStack(" + maxSize + ")";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new AllocStackExec(maxSize);
		}
	}

	class Call implements IInstr {
		protected int routAddress;

		public Call(int routAddress) {
			this.routAddress = routAddress;
		}

		public String toString() {
			return "Call(" + routAddress + ")";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new CallExec(routAddress);
		}
	}

	class Return implements IInstr {
		protected int size;

		public Return(int size) {
			this.size = size;
		}

		public String toString() {
			return "Return(" + size + ")";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new ReturnExec(size);
		}
	}

	// load immediate value (value -> stack)
	class LoadImInt implements IInstr {
		protected int value;

		public LoadImInt(int value) {
			this.value = value;
		}

		public String toString() {
			return "LoadImInt(" + value + ")";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new LoadImIntExec(value);
		}
	}

	// load immediate int64 value (value -> stack)
	class LoadImInt64 implements IInstr {
		protected long value;

		public LoadImInt64(long value) {
			this.value = value;
		}

		public String toString() {
			return "LoadImInt64(" + value + ")";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new LoadImInt64Exec(value);
		}
	}

	// load address relative to frame pointer (address -> stack)
	class LoadAddrRel implements IInstr {
		protected int relAddress;

		public LoadAddrRel(int relAddress) {
			this.relAddress = relAddress;
		}

		public String toString() {
			return "LoadAddrRel(" + relAddress + ")";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new LoadAddrRelExec(relAddress);
		}
	}

	// load instruction with address on stack
	// load (inside stack -> top of stack) operation
	class Deref implements IInstr {
		public String toString() {
			return "Deref";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new DerefExec();
		}
	}

	// store instruction with address on stack
	// store (top of stack -> inside stack) operation
	class Store implements IInstr {
		public String toString() {
			return "Store";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new StoreExec();
		}
	}

	// monadic instructions

	class NegInt implements IInstr {
		public String toString() {
			return "NegInt32";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new NegIntExec();
		}
	}

	class NegInt64 implements IInstr {
		public String toString() {
			return "NegInt64";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new NegInt64Exec();
		}
	}

	class InvBool implements IInstr {
		public String toString() {
			return "InvBool";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new InvBoolExec();
		}
	}

	// dyadic instructions

	class AddInt implements IInstr {
		public String toString() {
			return "AddInt";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new AddIntExec();
		}
	}

	class AddInt64 implements IInstr {
		public String toString() {
			return "AddInt64";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new AddInt64Exec();
		}
	}

	class SubInt implements IInstr {
		public String toString() {
			return "SubInt32";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new SubIntExec();
		}
	}

	class SubInt64 implements IInstr {
		public String toString() {
			return "SubInt64";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new SubInt64Exec();
		}
	}

	class MultInt implements IInstr {
		public String toString() {
			return "MultInt32";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new MultIntExec();
		}
	}

	class MultInt64 implements IInstr {
		public String toString() {
			return "MultInt64";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new MultInt64Exec();
		}
	}

	class DivTruncInt implements IInstr {
		public String toString() {
			return "DivTruncInt";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new DivTruncIntExec();
		}
	}

	class DivTruncInt64 implements IInstr {
		public String toString() {
			return "DivTruncInt64";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new DivTruncInt64Exec();
		}
	}

	class ModTruncInt implements IInstr {
		public String toString() {
			return "ModTruncInt";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new ModTruncIntExec();
		}
	}

	class ModTruncInt64 implements IInstr {
		public String toString() {
			return "ModTruncInt64";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new ModTruncInt64Exec();
		}
	}

	class EqInt implements IInstr {
		public String toString() {
			return "EqInt";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new EqIntExec();
		}
	}

	class EqInt64 implements IInstr {
		public String toString() {
			return "EqInt64";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new EqInt64Exec();
		}
	}

	class NeInt implements IInstr {
		public String toString() {
			return "NeInt";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new NeIntExec();
		}
	}

	class NeInt64 implements IInstr {
		public String toString() {
			return "NeInt64";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new NeInt64Exec();
		}
	}

	class GtInt implements IInstr {
		public String toString() {
			return "GtInt";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new GtIntExec();
		}
	}

	class GtInt64 implements IInstr {
		public String toString() {
			return "GtInt64";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new GtInt64Exec();
		}
	}

	class LtInt implements IInstr {
		public String toString() {
			return "LtInt";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new LtIntExec();
		}
	}

	class LtInt64 implements IInstr {
		public String toString() {
			return "LtInt64";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new LtInt64Exec();
		}
	}

	class GeInt implements IInstr {
		public String toString() {
			return "GeInt";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new GeIntExec();
		}
	}

	class GeInt64 implements IInstr {
		public String toString() {
			return "GeInt64";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new GeInt64Exec();
		}
	}

	class LeInt implements IInstr {
		public String toString() {
			return "LeInt";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new LeIntExec();
		}
	}

	class LeInt64 implements IInstr {
		public String toString() {
			return "LeInt64";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new LeInt64Exec();
		}
	}

	// jump instructions

	class UncondJump implements IInstr {
		protected int jumpAddr;

		public UncondJump(int jumpAddr) {
			this.jumpAddr = jumpAddr;
		}

		public String toString() {
			return "UncondJump(" + jumpAddr + ")";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new UncondJumpExec(jumpAddr);
		}
	}

	class CondJump implements IInstr {
		protected int jumpAddr;

		public CondJump(int jumpAddr) {
			this.jumpAddr = jumpAddr;
		}

		public String toString() {
			return "CondJump(" + jumpAddr + ")";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new CondJumpExec(jumpAddr);
		}
	}

	// input (input -> stack) and output (stack -> output) instructions

	class InputBool implements IInstr {
		protected String indicator;

		public InputBool(String indicator) {
			this.indicator = indicator;
		}

		public String toString() {
			return "InputBool(\"" + indicator + "\")";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new InputBoolExec(indicator);
		}
	}

	class InputInt implements IInstr {
		protected String indicator;

		public InputInt(String indicator) {
			this.indicator = indicator;
		}

		public String toString() {
			return "InputInt(\"" + indicator + "\")";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new InputIntExec(indicator);
		}
	}

	class InputInt64 implements IInstr {
		protected String indicator;

		public InputInt64(String indicator) {
			this.indicator = indicator;
		}

		public String toString() {
			return "InputInt64(\"" + indicator + "\")";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new InputInt64Exec(indicator);
		}
	}

	class OutputBool implements IInstr {
		protected String indicator;

		public OutputBool(String indicator) {
			this.indicator = indicator;
		}

		public String toString() {
			return "OutputBool(\"" + indicator + "\")";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new OutputBoolExec(indicator);
		}
	}

	class OutputInt implements IInstr {
		protected String indicator;

		public OutputInt(String indicator) {
			this.indicator = indicator;
		}

		public String toString() {
			return "OutputInt(\"" + indicator + "\")";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new OutputIntExec(indicator);
		}
	}

	class OutputInt64 implements IInstr {
		protected String indicator;

		public OutputInt64(String indicator) {
			this.indicator = indicator;
		}

		public String toString() {
			return "OutputInt64(\"" + indicator + "\")";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new OutputInt64Exec(indicator);
		}
	}

	// cast instructions

	class PromoteInt32ToInt64 implements IInstr {
		public PromoteInt32ToInt64() {
		}

		public String toString() {
			return "PromoteInt32ToInt64";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new PromoteInt32ToInt64Exec();
		}
	}

	class CastInt64ToInt32Clamp implements IInstr {
		public CastInt64ToInt32Clamp() {
		}

		public String toString() {
			return "CastInt64ToInt32Clamp";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new CastInt64ToInt32ClampExec();
		}
	}

	class CastInt64ToInt32Cut implements IInstr {
		public CastInt64ToInt32Cut() {
		}

		public String toString() {
			return "CastInt64ToInt32Cut";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new CastInt64ToInt32CutExec();
		}
	}

	class CastInt64ToInt32Lossless implements IInstr {
		public CastInt64ToInt32Lossless() {
		}

		public String toString() {
			return "CastInt64ToInt32Lossless";
		}

		public IExecInstr toExecInstr(VirtualMachine vm) {
			return vm.new CastInt64ToInt32LosslessExec();
		}
	}
}
