package net.tefyert.techatech.api.block.entity;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityUnloadable {
    /**
     * Checks if the TileEntity is Invalid or Unloaded. Stupid Minecraft cannot do that for the Unloaded Check btw.
     * Implementing this Function properly is very important, and should be required for every TileEntity.
     * That is why I made it a separate Interface and forced it as super Interface to all my other Interfaces.
     *
     * To do it properly just add a true Boolean Flag to your Member Variables ("mIsDead=false" for example) and set it to true when "onChunkUnload" is called. Then return the following:
     *
     * @return mIsDead || isInvalid()
     */
    public boolean isDead();
}