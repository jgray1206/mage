package mage.abilities.common;

import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.effects.Effect;
import mage.constants.Zone;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;

/**
 * @author stravant
 */
public class WheneverYouExertCreatureTriggeredAbility extends TriggeredAbilityImpl {

    public WheneverYouExertCreatureTriggeredAbility(Effect effect) {
        super(Zone.BATTLEFIELD, effect);
    }

    public WheneverYouExertCreatureTriggeredAbility(final WheneverYouExertCreatureTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.BECOMES_EXERTED;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        boolean weAreExerting = getControllerId().equals(event.getPlayerId());
        Permanent exerted = game.getPermanent(event.getTargetId());
        boolean exertedIsCreature = (exerted != null) && exerted.isCreature();
        return weAreExerting && exertedIsCreature;
    }

    @Override
    public WheneverYouExertCreatureTriggeredAbility copy() {
        return new WheneverYouExertCreatureTriggeredAbility(this);
    }

    @Override
    public String getRule() {
        return "Whenever you exert a creature, " + super.getRule();
    }
}
