import java.util.ArrayList;
import java.util.List;

public class PlayersDBMock implements IPlayer {

    private final ArrayList<Player> players = new ArrayList<>();

    public PlayersDBMock() {
        addMockPlayers();
    }

    private void addMockPlayers() {
        players.add(new Player("Devil Killer", new Crossbow()));
        players.add(new Player("Jet Healer", new Pistol(), null, new Helmet()));
        players.add(new Player("Phoenix", new Sniper(), new Vest(), new Helmet()));
        players.add(new Player("Enormous Giant", new GrenadeLauncher(), new Vest()));
    }

    public Player getPlayerByIndex(int index) {
        return players.get(index);
    }

    @Override
    public NewPlayerState createNewPlayer(Player player) {
        if (player.getName().equals("")) return NewPlayerState.INCORRECT_DATA;

        return NewPlayerState.SUCCESS;
    }

    public UpdatePlayerState updatePlayer(Player player) {
        int index = findPlayerById(player.getUniqueId());
        if (index == -1) {
            return UpdatePlayerState.FAILURE;
        }
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

    public List<Player> getAllPlayers() {
        return players;
    }
}
