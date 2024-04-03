package io.goorm.etdservice.domain.servers.dto.vo;

public class SystemData {
    private int cpu;
    private int ram;

    public SystemData(int cpu, int ram) {
        this.cpu = cpu;
        this.ram = ram;
    }
}
