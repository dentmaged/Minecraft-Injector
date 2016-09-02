package me.dentayyy.minecraftinjector;

import java.io.File;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

public class Injector {

    public static void inject() throws Exception {
        System.out.println("Injecting...");
        String pid = null;
        for (VirtualMachineDescriptor jvm : VirtualMachine.list()) {
            System.out.println(jvm.displayName());
            if ((jvm.displayName().startsWith("net.minecraft.client.main.Main")) || (jvm.displayName().startsWith("net.minecraft.launchwrapper.Launch")) || (jvm.displayName().startsWith("magic.launcher.Launcher"))) {
                pid = jvm.id();
            }
        }
        System.out.println(pid);
        if (pid != null) {
            VirtualMachine vm = VirtualMachine.attach(pid);
            vm.loadAgent(new File("agent.jar").getAbsolutePath());
            vm.detach();
        }
        System.out.println("Injected!");
    }

}