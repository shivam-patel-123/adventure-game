import java.util.ArrayList;

public class PlayersDB implements IPlayer {

    // Assume this as our real database.
    private ArrayList<Player> players = new ArrayList<>();

    public NewPlayerState createNewPlayer(Player player) {
        if (player.getName().equals("")) {
            return NewPlayerState.INCORRECT_DATA;
        }

        players.add(player);
        return NewPlayerState.SUCCESS;
    }

    public UpdatePlayerState updatePlayer(Player player) {
        int index = findPlayerById(player.getUniqueId());
        if (index == -1) {
            return UpdatePlayerState.FAILURE;
        }
        players.set(index, player);
        return UpdatePlayerState.SUCCESS;
    }

    private int findPlayerById(String uniqueId) {
        for (int i = 0; i<players.size(); ++i)
        {
            if (players.get(i).getUniqueId().equals(uniqueId)) {
                return i;
            }
        }
        return -1;
    }
}

