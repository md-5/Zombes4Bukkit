package com.md_5.growth;

import com.md_5.zmod.BaseMod;

public class Growth extends BaseMod {

    final static int rootTime = 10;

    @Override
    public void enable() {
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Handler(), rootTime * 20, rootTime * 20);
    }

    @Override
    public void disable() {
        getServer().getScheduler().cancelTasks(this);
    }

    @Override
    public void init() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update() {
    }
    /*private static boolean modGrowthEnabled;
    private static boolean optGrowthRooting, optGrowthPlanting;
    private static int optGrowthFlower, optGrowthShroom, optGrowthPumpkin, optGrowthSappling, optGrowthReed, optGrowthRootingSpace, optGrowthRootingTime;
    private static float growthSqrRadius;

    private static boolean initModGrowth() {
        // TODO config
        optionsModGrowth();
        return true;
    }

    private static void optionsModGrowth() {
        // TODO optGrowthRooting = getSetBool(optGrowthRooting, "optGrowthRooting", true, "Auto root sapplings");
    }

    private static void updateModGrowth(List<LivingEntity> list, World world) {
        Random rnd = new Random();
        int pX = fix(posX) >> 4, pZ = fix(posZ) >> 4;
        for (int cX = -15; cX <= 15; cX++) {
            for (int cZ = -15; cZ <= 15; cZ++) {
                if (world.getChunkAt(pX + cX, pZ + cZ) != null) {
                    byte arr[] = world.getMultiChunkData(pX + cX, 0, pZ + cZ, 16, world.height, 16);
                    int pos = (rnd.nextInt(256) << 7) + 1, X = (pos >> 11) + ((pX + cX) << 4), Y, Z = ((pos >> 7) & 15) + ((pZ + cZ) << 4), blockId, meta;
                    for (Y = 1; Y < 128; Y++, pos++) {
                        if ((block[blockId = ((int) arr[pos] & 255)] & GROW) != 0 && arr[pos - 1] == 3) {
                            int chance = Integer.MAX_VALUE;
                            switch (blockId) {
                                case 6:
                                    chance = optGrowthSappling;
                                    break; // sappling
                                case 37:
                                case 38:
                                    chance = optGrowthFlower;
                                    break; // flower
                                case 39:
                                case 40:
                                    chance = optGrowthShroom;
                                    break; // shroom
                                case 83:
                                    chance = optGrowthReed;
                                    break; // reed
                                case 86:
                                    chance = optGrowthPumpkin;
                                    break; // pumpkin
                            }
                            if (chance >= 1000 || rnd.nextInt(chance) != 0) {
                                continue;
                            }
                            int rX = rnd.nextInt(3) - 1, rZ = rnd.nextInt(3) - 1;
                            if (world.getTypeId(X + rX, Y - 1, Z + rZ) != 3 || world.getTypeId(X + rX, Y, Z + rZ) != 0) {
                                continue;
                            }
                            int light = world.getLightLevel(X + rX, Y, Z + rZ);
                            if (blockId == 39 || blockId == 40) {
                                if (light > 13) {
                                    continue;
                                }
                            } else if (light < 8) {
                                continue; // light check
                            }
                            world.setTypeIdAndData(X + rX, Y, Z + rZ, blockId, world.getData(X, Y, Z));
                        }
                    }
                }
            }
        }
    }
    private static int block[] = new int[256];

    private static void initBlockLookupArray() {
        block[  0] = KNOWN | SPACE | BASIC | EMPTY; // air
        block[  1] = KNOWN | SOLID | BASIC;         // stone
        block[  2] = KNOWN | SOLID | GRASS;         // grass
        block[  3] = KNOWN | SOLID | BASIC;         // dirt
        block[  4] = KNOWN | SOLID | COBBLE;        // cobble
        block[  5] = KNOWN | SOLID | CRAFT;         // plank
        block[  6] = KNOWN | SPACE | DECAL | GROW;  // sapling
        block[  7] = KNOWN | SOLID | BASIC;         // bedrock
        block[  8] = KNOWN | SPACE | LIQUID;        // water - updating
        block[  9] = KNOWN | SPACE | LIQUID;        // water
        block[ 10] = KNOWN | LIQUID;                // lava - updating
        block[ 11] = KNOWN | LIQUID;                // lava
        block[ 12] = KNOWN | SOLID | SAND;          // sand
        block[ 13] = KNOWN | SOLID | GRAVEL;        // gravel
        block[ 14] = KNOWN | SOLID | ORE | BASIC;   // gold ore
        block[ 15] = KNOWN | SOLID | ORE | BASIC;   // iron ore
        block[ 16] = KNOWN | SOLID | ORE | BASIC;   // coal ore
        block[ 17] = KNOWN | SOLID | TREE;          // trunk
        block[ 18] = KNOWN | SOLID | TREE;          // leaves
        block[ 19] = KNOWN | SOLID | CRAFT;         // sponge
        block[ 20] = KNOWN | SOLID | CRAFT;         // glass
        block[ 21] = KNOWN | SOLID | ORE | BASIC;   // blue ore
        block[ 22] = KNOWN | SOLID | CRAFT;         // blue block
        block[ 23] = KNOWN | SOLID | CRAFT | STORAGE;// dispenser
        block[ 24] = KNOWN | SOLID | SANDSTONE;     // sandstone
        block[ 25] = KNOWN | SOLID | CRAFT | TOUCH; // note
        block[ 26] = KNOWN | SOLID | CRAFT;         // bed
        block[ 27] = KNOWN | SPACE | DECAL;         // rail booster
        block[ 28] = KNOWN | SPACE | DECAL;         // rail detector
        block[ 30] = KNOWN | SPACE | DECAL;         // web
        block[ 31] = KNOWN | SPACE | DECAL;         // tall grass
        block[ 32] = KNOWN | SPACE | DECAL;         // dead shrubs
        block[ 33] = KNOWN | SOLID | CRAFT;         // piston
        block[ 34] = KNOWN | SOLID | CRAFT;         // piston plate
        block[ 35] = KNOWN | SOLID | CRAFT;         // wool
        block[ 36] = KNOWN | SOLID;                 // block moved by piston / aka. reserved slot  - it is indestructible
        block[ 37] = KNOWN | SPACE | DECAL | GROW;  // yellow flower
        block[ 38] = KNOWN | SPACE | DECAL | GROW;  // red flower
        block[ 39] = KNOWN | SPACE | DECAL | GROW;  // brown mushroom
        block[ 40] = KNOWN | SPACE | DECAL | GROW;  // red mushroom
        block[ 41] = KNOWN | SOLID | CRAFT;         // gold block
        block[ 42] = KNOWN | SOLID | CRAFT;         // iron block
        block[ 43] = KNOWN | SOLID | CRAFT;         // double slab
        block[ 44] = KNOWN | SOLID | CRAFT;         // slab
        block[ 45] = KNOWN | SOLID | CRAFT;         // brick
        block[ 46] = KNOWN | SOLID | CRAFT;         // tnt
        block[ 47] = KNOWN | SOLID | CRAFT;         // bookshelf
        block[ 48] = KNOWN | SOLID | BASIC;         // mossy
        block[ 49] = KNOWN | SOLID | OBSIDIAN;      // obsidian
        block[ 50] = KNOWN | SPACE | DECAL;         // torch
        block[ 51] = KNOWN | SPACE | DECAL;         // fire
        block[ 52] = KNOWN | SOLID | BASIC;         // spawner
        block[ 53] = KNOWN | SOLID | CRAFT;         // wooden stairs
        block[ 54] = KNOWN | SOLID | CRAFT | STORAGE;// chest
        block[ 55] = KNOWN | SPACE | DECAL;         // wire
        block[ 56] = KNOWN | SOLID | ORE | BASIC;   // diamond ore
        block[ 57] = KNOWN | SOLID | CRAFT;         // diamond block
        block[ 58] = KNOWN | SOLID | CRAFT;         // bench
        block[ 59] = KNOWN | SPACE | DECAL;         // wheat
        block[ 60] = KNOWN | SOLID | CRAFT;         // farmland
        block[ 61] = KNOWN | SOLID | CRAFT | STORAGE;// furnace
        block[ 62] = KNOWN | SOLID | CRAFT | STORAGE;// furnace - burning
        block[ 63] = KNOWN | SPACE | DECAL | TOUCH; // sign on post
        block[ 64] = KNOWN | SPACE | DECAL | TOUCH; // wooden door
        block[ 65] = KNOWN | SPACE | DECAL;         // ladder
        block[ 66] = KNOWN | SPACE | DECAL;         // rails
        block[ 67] = KNOWN | SOLID | CRAFT;         // cobble stairs
        block[ 68] = KNOWN | SPACE | DECAL | TOUCH; // sign on wall
        block[ 69] = KNOWN | SPACE | DECAL | TOUCH; // lever
        block[ 70] = KNOWN | SPACE | DECAL;         // stone plate
        block[ 71] = KNOWN | SPACE | DECAL | TOUCH; // iron door
        block[ 72] = KNOWN | SPACE | DECAL;         // wooden plate
        block[ 73] = KNOWN | SOLID | ORE | BASIC;   // redstone ore
        block[ 74] = KNOWN | SOLID | ORE | BASIC;   // redstone ore - glowing
        block[ 75] = KNOWN | SPACE | DECAL;         // red torch - off
        block[ 76] = KNOWN | SPACE | DECAL;         // red torch - on
        block[ 77] = KNOWN | SPACE | DECAL | TOUCH; // button
        block[ 78] = KNOWN | SPACE;                 // snowcap
        block[ 79] = KNOWN | SOLID | BASIC;         // ice
        block[ 80] = KNOWN | SOLID | CRAFT;         // snow block
        block[ 81] = KNOWN | SOLID | BASIC;         // cactus
        block[ 82] = KNOWN | SOLID | BASIC;         // clay
        block[ 83] = KNOWN | SPACE | DECAL | GROW;  // reed
        block[ 84] = KNOWN | SOLID | CRAFT;         // jukebox
        block[ 85] = KNOWN | SOLID | CRAFT;         // fence
        block[ 86] = KNOWN | SOLID | BASIC | GROW;  // pumpkin
        block[ 87] = KNOWN | SOLID | BASIC;         // netherrack
        block[ 88] = KNOWN | SOLID | BASIC;         // soul sand
        block[ 89] = KNOWN | SOLID | ORE | BASIC;   // glowstone
        block[ 90] = KNOWN | SPACE;                 // portal
        block[ 91] = KNOWN | SOLID | CRAFT;         // pumpkin - torch
        block[ 92] = KNOWN | SOLID | CRAFT | TOUCH; // cacke
        block[ 93] = KNOWN | SOLID | CRAFT | TOUCH; // redstone repeater - off
        block[ 94] = KNOWN | SOLID | CRAFT | TOUCH; // redstone repeater - on
        block[ 95] = KNOWN | SOLID | CRAFT;         // locked chest
        block[ 96] = KNOWN | SOLID | CRAFT;         // trapdoor
        block[ 97] = KNOWN | SOLID | BASIC;         // stone with silverfish in it
        block[ 98] = KNOWN | SOLID | BASIC;         // stone brick
        block[ 99] = KNOWN | SOLID | BASIC;         // brown scroom block
        block[100] = KNOWN | SOLID | BASIC;         // red shroom block
        block[101] = KNOWN | SOLID | BASIC;         // iron bars
        block[102] = KNOWN | SOLID | CRAFT;         // glass plane
        block[103] = KNOWN | SOLID | BASIC;         // melon
        block[104] = KNOWN | SPACE | DECAL;         // pumpkin seeds
        block[105] = KNOWN | SPACE | DECAL;         // melon seeds
        block[106] = KNOWN | SPACE | DECAL;         // vines
        block[107] = KNOWN | SOLID | CRAFT;         // fence gate
        block[108] = KNOWN | SOLID | CRAFT;         // brick stairs
        block[109] = KNOWN | SOLID | CRAFT;         // stone brick stairs
        block[110] = KNOWN | SOLID | BASIC;         // mycelium
        block[111] = KNOWN | SPACE | BASIC;         // lily pad
        block[112] = KNOWN | SOLID | BASIC;         // nether brick (TODO: disable spawn option)
        block[113] = KNOWN | SOLID | CRAFT;         // nether brick fence
        block[114] = KNOWN | SOLID | CRAFT;         // nether brick stairs
        block[115] = KNOWN | SPACE | BASIC;         // nether wart
        block[116] = KNOWN | SOLID | CRAFT;         // enchantment table
        block[117] = KNOWN | SOLID | CRAFT;         // brewing stand
        block[118] = KNOWN | SOLID | CRAFT;         // cauldron
        block[119] = KNOWN | SPACE;                 // air portal
        block[120] = KNOWN | SOLID | CRAFT;         // air portal frame
        block[121] = KNOWN | SOLID | BASIC;         // end stone
        block[122] = KNOWN | SOLID | BASIC;         // dragon egg
        for (int i = 0; i < 256; i++) {
            if (getBlockIsSpawn(i)) {
                block[i] |= SPAWN;
            }
        }
    }
    private static final int KNOWN = 0x00000001, SOLID = 0x00000002, LIQUID = 0x00000004, CRAFT = 0x00000008,
            BASIC = 0x00000010, SPACE = 0x00000020, TREE = 0x00000040, GRASS = 0x00000080,
            COBBLE = 0x00000100, DECAL = 0x00000200, SAND = 0x00000400, GRAVEL = 0x00000800,
            ORE = 0x00001000, OBSIDIAN = 0x00002000, SPAWN = 0x00004000, TOUCH = 0x00008000,
            SANDSTONE = 0x00010000, GROW = 0x00020000, STORAGE = 0x00040000, EMPTY = 0x00080000;

    private static boolean getBlockIsSpawn(int id) {
        return Block.byId[id] != null && Block.byId[id].material.isSolid() && Block.byId[id].b();
    }*/
}
