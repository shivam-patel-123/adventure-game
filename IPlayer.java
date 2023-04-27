public interface IPlayer {

    enum NewPlayerState {
        SUCCESS,
        INCORRECT_DATA,
        FAILURE
    }

    enum UpdatePlayerState {
        SUCCESS,
        FAILURE
    }

    NewPlayerState createNewPlayer(Player player);
    UpdatePlayerState updatePlayer(Player player);

}
