package net.timeless.jurassicraft.common.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;

public class ItemDinosaurEgg extends ItemDnaContainer
{
    public ItemDinosaurEgg()
    {
        super();

        this.setUnlocalizedName("dino_egg");
        this.setMaxStackSize(1);

        this.setCreativeTab(JCCreativeTabs.eggs);
    }

    public String getItemStackDisplayName(ItemStack stack)
    {
        String dinoName = getDinosaur(stack).getName().toLowerCase().replaceAll(" ", "_");

        return StatCollector.translateToLocal("entity." + dinoName + ".name") + " " + StatCollector.translateToLocal("item.dino_egg.name");
    }

    public Dinosaur getDinosaur(ItemStack stack)
    {
        return JCEntityRegistry.getDinosaurById(stack.getMetadata());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List subtypes)
    {
        int i = 0;

        for (Dinosaur dino : JCEntityRegistry.getDinosaurs())
        {
            if (dino.shouldRegister())
                subtypes.add(new ItemStack(item, 1, i));

            i++;
        }
    }
}