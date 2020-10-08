package pixelmoba.shared.dto;


public class PlayerDisconnectDto implements Dto {

    public PlayerDisconnectDto() {
    }

    public PlayerDisconnectDto(long id) {
        this.id = id;
    }

    public long id = -1;
}
