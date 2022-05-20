package pt.epcc.alunos.al220007.entities.players;

import pt.epcc.alunos.al220007.game.Game;

public class Human extends Player{
    public Human() {
        super(Game.frameWidth / 2 - 64 / 2, Game.frameHeight - 64);
    }
}
