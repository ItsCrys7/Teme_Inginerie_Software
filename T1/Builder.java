class Computer {
    String cpu, gpu, ram;
}

interface ComputerBuild {
    void setCPU(String cpu);
    void setGPU(String gpu);
    void setRAM(String ram);
    Computer build();
}

class GamingComputerBuild implements ComputerBuilder {
    private Computer pc = new Computer();
    public void setCPU(String cpu) {
        pc.cpu = cpu;
    }
    public void setGPU(String gpu) {
        pc.cpu = gpu;
    }
    public void setRAM(String ram) {
        pc.cpu = ram;
    }
    public Computer build() {
        return pc;
    }
}

class Dorector{
    void constructComputerPC(ComputerBuilder builder){
        builder.setCPU("Intel i7");
        builder.setGPU("vPro 8th Gen");
        builder.setRAM("24GB");
    }
}
