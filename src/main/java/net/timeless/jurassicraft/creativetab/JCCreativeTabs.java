package net.timeless.jurassicraft.creativetab;

import net.minecraft.item.Item;
import net.timeless.jurassicraft.block.JCBlockRegistry;
import net.timeless.jurassicraft.item.JCItemRegistry;

public class JCCreativeTabs
{
    public static CreativeTabJurassiCraft items;
    public static CreativeTabJurassiCraft blocks;

    public static CreativeTabJurassiCraft dna;
    public static CreativeTabJurassiCraft eggs;
    public static CreativeTabJurassiCraft spawnEggs;

    public void register()
    {
        items = new CreativeTabJurassiCraft("jurassicraft.items")
        {
            public Item getTabIconItem()
            {
                return JCItemRegistry.fossil;
            }
        };

        dna = new CreativeTabJurassiCraft("jurassicraft.dna")
        {
            public Item getTabIconItem()
            {
                return JCItemRegistry.dna;
            }
        };

        eggs = new CreativeTabJurassiCraft("jurassicraft.eggs")
        {
            public Item getTabIconItem()
            {
                return JCItemRegistry.egg;
            }
        };

        spawnEggs = new CreativeTabJurassiCraft("jurassicraft.spawnEggs")
        {
            public Item getTabIconItem()
            {
                return JCItemRegistry.spawn_egg;
            }
        };

        blocks = new CreativeTabJurassiCraft("jurassicraft.blocks")
        {
            public Item getTabIconItem()
            {
                return Item.getItemFromBlock(JCBlockRegistry.encased_fossil);
            }
        };
    }
}