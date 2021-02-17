package com.example.ReportPlayer.services.report.player;

import com.example.ReportPlayer.exception.PlayerException;
import com.example.ReportPlayer.models.report.player.Player;
import com.example.ReportPlayer.repository.report.player.PlayerBaseRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PlayerServiceImpl implements PlayerService {

    private PlayerBaseRepository repository;

    public PlayerServiceImpl(PlayerBaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Player save(Player player) {
        final Player playerExpected = (Player) repository.findByNickname(player.getNickname());
        if(playerExpected != null) {
            playerExpected.setDate(new Date());
            playerExpected.setReportCount(playerExpected.getReportCount()+1);
            return playerExpected;
        }
        player.setDate(new Date());
        player.setReportCount(player.getReportCount()+1);
        return (Player) repository.save(player);
    }

    @Override
    public Player findByNickname(String nickname) {
        return (Player) repository.findByNickname(nickname);
    }

    @Override
    public Player delete(long id) {
        Player player = (Player) repository.getOne(id);
        if(player == null) {
            throw new PlayerException("Player not found");
        }
        if(checkIfReportCountNeedToDecrement(player)) {
            return player;
        }
        else {
            repository.deleteById(player.getId());
            player = null;
        }
        return player;
    }

    @Override
    public Player findByPlayer(Player p) {
        Optional playerExpected = repository.findById(p.getId());
        Player player = (Player) playerExpected.get();
        if(player== null) {
            return null;
        }
        return player;
    }
    @Override
    public List<Player> getTopPlayers() {
        return repository.findTop5ByOrderByReportCountDesc();
    }

    @Override
    public List<Player> getLastReportedPlayers() {
        return repository.findTop3ByOrderByDateDesc();
    }

    @Override
    public Player getPlayerById(long id) {
        return null;
    }

    private boolean checkIfReportCountNeedToDecrement(Player player) {
        if(player.getReportCount()>1) {
            player.setReportCount(player.getReportCount()-1);
            return true;
        }
        return false;
    }

}
