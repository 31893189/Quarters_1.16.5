package net.earthmc.quarters.command.quartersadmin.method;

import com.palmergames.bukkit.towny.object.Resident;
import net.earthmc.quarters.api.QuartersMessaging;
import net.earthmc.quarters.object.base.CommandMethod;
import net.earthmc.quarters.object.entity.Quarter;
import net.earthmc.quarters.object.exception.CommandMethodException;
import net.earthmc.quarters.object.wrapper.StringConstants;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminEvictMethod extends CommandMethod {

    public AdminEvictMethod(CommandSender sender, String[] args) {
        super(sender, args, "quarters.command.quartersadmin.evict");
    }

    @Override
    public void execute() {
        Player player = getSenderAsPlayerOrThrow();
        Quarter quarter = getQuarterAtPlayerOrThrow(player);

        Resident owner = quarter.getOwnerResident();
        if (owner == null) throw new CommandMethodException(StringConstants.THIS_QUARTER_HAS_NO_OWNER);

        quarter.setOwner(null);

        QuartersMessaging.sendSuccessMessage(player, "Successfully evicted " + owner.getName());
    }
}
