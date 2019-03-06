package com.leekwars.game.attack.effect;

import com.leekwars.game.fight.Fight;
import com.leekwars.game.fight.entity.Entity;

public class EffectShackleMP extends Effect {

	@Override
	public void apply(Fight fight) {

		// Base shackle : base × (1 + magic / 100)
		value = (int) Math.round((value1 + jet * value2) * (1.0 + Math.max(0, caster.getMagic()) / 100.0) * power * criticalPower);

		stats.setStat(Entity.CHARAC_MP, -value);
		target.updateBuffStats(Entity.CHARAC_MP);
	}
	
	public void reduce() {
		value /= 2;
		stats.setStat(Entity.CHARAC_MP, -value);
		target.updateBuffStats(Entity.CHARAC_MP);
	}
}
