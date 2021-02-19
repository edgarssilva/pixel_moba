package pixelmoba.shared;

public class Network {
    private int timeout;
    private int tcpPort;
    private int udpPort;
    private String host;

    public Network() {

    }

    public Network(int timeout, int tcpPort, int udpPort, String host) {
        this.timeout = timeout;
        this.tcpPort = tcpPort;
        this.udpPort = udpPort;
        this.host = host;
    }

    public int getTimeout() {
        return timeout;
    }

    public int getTcpPort() {
        return tcpPort;
    }

    public int getUdpPort() {
        return udpPort;
    }

    public String getHost() {
        return host;
    }
}

