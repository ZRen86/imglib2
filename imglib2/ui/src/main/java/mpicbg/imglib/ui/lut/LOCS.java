package mpicbg.imglib.ui.lut;

//==============================================================================
// LOCS ColorRGB SCALE Class
//
//   Dr. Haim Levkowitz, UMass Lowell (perceptually linearized)
// 
// AGG - Alexander Gee
//
// 041497 - created
//==============================================================================
public class LOCS 
{
	static public int size = 256;  // 256 defined RGB ColorRGBs

	static public ColorRGB[] rgb;

	public LOCS()
	{
		rgb = new ColorRGB[size];

		rgb[  0] = new ColorRGB(   0,   0,   0 );
		rgb[  1] = new ColorRGB(   0,   0,   0 );
		rgb[  2] = new ColorRGB(   0,   0,   0 );
		rgb[  3] = new ColorRGB(   1,   0,   0 );
		rgb[  4] = new ColorRGB(   2,   0,   0 );
		rgb[  5] = new ColorRGB(   2,   0,   0 );
		rgb[  6] = new ColorRGB(   3,   0,   0 );
		rgb[  7] = new ColorRGB(   3,   0,   0 );
		rgb[  8] = new ColorRGB(   4,   0,   0 );
		rgb[  9] = new ColorRGB(   5,   0,   0 );
		rgb[ 10] = new ColorRGB(   5,   0,   0 );
		rgb[ 11] = new ColorRGB(   6,   0,   0 );
		rgb[ 12] = new ColorRGB(   7,   0,   0 );
		rgb[ 13] = new ColorRGB(   7,   0,   0 );
		rgb[ 14] = new ColorRGB(   8,   0,   0 );
		rgb[ 15] = new ColorRGB(   9,   0,   0 );
		rgb[ 16] = new ColorRGB(   9,   0,   0 );
		rgb[ 17] = new ColorRGB(  10,   0,   0 );
		rgb[ 18] = new ColorRGB(  11,   0,   0 );
		rgb[ 19] = new ColorRGB(  12,   0,   0 );
		rgb[ 20] = new ColorRGB(  13,   0,   0 );
		rgb[ 21] = new ColorRGB(  14,   0,   0 );
		rgb[ 22] = new ColorRGB(  15,   0,   0 );
		rgb[ 23] = new ColorRGB(  16,   0,   0 );
		rgb[ 24] = new ColorRGB(  17,   0,   0 );
		rgb[ 25] = new ColorRGB(  18,   0,   0 );
		rgb[ 26] = new ColorRGB(  19,   0,   0 );
		rgb[ 27] = new ColorRGB(  20,   0,   0 );
		rgb[ 28] = new ColorRGB(  21,   0,   0 );
		rgb[ 29] = new ColorRGB(  22,   0,   0 );
		rgb[ 30] = new ColorRGB(  23,   0,   0 );
		rgb[ 31] = new ColorRGB(  25,   0,   0 );
		rgb[ 32] = new ColorRGB(  26,   0,   0 );
		rgb[ 33] = new ColorRGB(  27,   0,   0 );
		rgb[ 34] = new ColorRGB(  28,   0,   0 );
		rgb[ 35] = new ColorRGB(  30,   0,   0 );
		rgb[ 36] = new ColorRGB(  31,   0,   0 );
		rgb[ 37] = new ColorRGB(  33,   0,   0 );
		rgb[ 38] = new ColorRGB(  34,   0,   0 );
		rgb[ 39] = new ColorRGB(  35,   0,   0 );
		rgb[ 40] = new ColorRGB(  37,   0,   0 );
		rgb[ 41] = new ColorRGB(  39,   0,   0 );
		rgb[ 42] = new ColorRGB(  40,   0,   0 );
		rgb[ 43] = new ColorRGB(  43,   0,   0 );
		rgb[ 44] = new ColorRGB(  45,   0,   0 );
		rgb[ 45] = new ColorRGB(  46,   0,   0 );
		rgb[ 46] = new ColorRGB(  49,   0,   0 );
		rgb[ 47] = new ColorRGB(  51,   0,   0 );
		rgb[ 48] = new ColorRGB(  53,   0,   0 );
		rgb[ 49] = new ColorRGB(  54,   0,   0 );
		rgb[ 50] = new ColorRGB(  56,   0,   0 );
		rgb[ 51] = new ColorRGB(  58,   0,   0 );
		rgb[ 52] = new ColorRGB(  60,   0,   0 );
		rgb[ 53] = new ColorRGB(  62,   0,   0 );
		rgb[ 54] = new ColorRGB(  64,   0,   0 );
		rgb[ 55] = new ColorRGB(  67,   0,   0 );
		rgb[ 56] = new ColorRGB(  69,   0,   0 );
		rgb[ 57] = new ColorRGB(  71,   0,   0 );
		rgb[ 58] = new ColorRGB(  74,   0,   0 );
		rgb[ 59] = new ColorRGB(  76,   0,   0 );
		rgb[ 60] = new ColorRGB(  80,   0,   0 );
		rgb[ 61] = new ColorRGB(  81,   0,   0 );
		rgb[ 62] = new ColorRGB(  84,   0,   0 );
		rgb[ 63] = new ColorRGB(  86,   0,   0 );
		rgb[ 64] = new ColorRGB(  89,   0,   0 );
		rgb[ 65] = new ColorRGB(  92,   0,   0 );
		rgb[ 66] = new ColorRGB(  94,   0,   0 );
		rgb[ 67] = new ColorRGB(  97,   0,   0 );
		rgb[ 68] = new ColorRGB( 100,   0,   0 );
		rgb[ 69] = new ColorRGB( 103,   0,   0 );
		rgb[ 70] = new ColorRGB( 106,   0,   0 );
		rgb[ 71] = new ColorRGB( 109,   0,   0 );
		rgb[ 72] = new ColorRGB( 112,   0,   0 );
		rgb[ 73] = new ColorRGB( 115,   0,   0 );
		rgb[ 74] = new ColorRGB( 117,   0,   0 );
		rgb[ 75] = new ColorRGB( 122,   0,   0 );
		rgb[ 76] = new ColorRGB( 126,   0,   0 );
		rgb[ 77] = new ColorRGB( 128,   0,   0 );
		rgb[ 78] = new ColorRGB( 131,   0,   0 );
		rgb[ 79] = new ColorRGB( 135,   0,   0 );
		rgb[ 80] = new ColorRGB( 135,   0,   0 );
		rgb[ 81] = new ColorRGB( 135,   1,   0 );
		rgb[ 82] = new ColorRGB( 135,   2,   0 );
		rgb[ 83] = new ColorRGB( 135,   3,   0 );
		rgb[ 84] = new ColorRGB( 135,   4,   0 );
		rgb[ 85] = new ColorRGB( 135,   6,   0 );
		rgb[ 86] = new ColorRGB( 135,   6,   0 );
		rgb[ 87] = new ColorRGB( 135,   8,   0 );
		rgb[ 88] = new ColorRGB( 135,   9,   0 );
		rgb[ 89] = new ColorRGB( 135,  10,   0 );
		rgb[ 90] = new ColorRGB( 135,  11,   0 );
		rgb[ 91] = new ColorRGB( 135,  13,   0 );
		rgb[ 92] = new ColorRGB( 135,  13,   0 );
		rgb[ 93] = new ColorRGB( 135,  15,   0 );
		rgb[ 94] = new ColorRGB( 135,  17,   0 );
		rgb[ 95] = new ColorRGB( 135,  17,   0 );
		rgb[ 96] = new ColorRGB( 135,  19,   0 );
		rgb[ 97] = new ColorRGB( 135,  21,   0 );
		rgb[ 98] = new ColorRGB( 135,  22,   0 );
		rgb[ 99] = new ColorRGB( 135,  23,   0 );
		rgb[100] = new ColorRGB( 135,  25,   0 );
		rgb[101] = new ColorRGB( 135,  26,   0 );
		rgb[102] = new ColorRGB( 135,  27,   0 );
		rgb[103] = new ColorRGB( 135,  29,   0 );
		rgb[104] = new ColorRGB( 135,  31,   0 );
		rgb[105] = new ColorRGB( 135,  32,   0 );
		rgb[106] = new ColorRGB( 135,  33,   0 );
		rgb[107] = new ColorRGB( 135,  35,   0 );
		rgb[108] = new ColorRGB( 135,  36,   0 );
		rgb[109] = new ColorRGB( 135,  38,   0 );
		rgb[110] = new ColorRGB( 135,  40,   0 );
		rgb[111] = new ColorRGB( 135,  42,   0 );
		rgb[112] = new ColorRGB( 135,  44,   0 );
		rgb[113] = new ColorRGB( 135,  46,   0 );
		rgb[114] = new ColorRGB( 135,  47,   0 );
		rgb[115] = new ColorRGB( 135,  49,   0 );
		rgb[116] = new ColorRGB( 135,  51,   0 );
		rgb[117] = new ColorRGB( 135,  52,   0 );
		rgb[118] = new ColorRGB( 135,  54,   0 );
		rgb[119] = new ColorRGB( 135,  56,   0 );
		rgb[120] = new ColorRGB( 135,  57,   0 );
		rgb[121] = new ColorRGB( 135,  59,   0 );
		rgb[122] = new ColorRGB( 135,  62,   0 );
		rgb[123] = new ColorRGB( 135,  63,   0 );
		rgb[124] = new ColorRGB( 135,  65,   0 );
		rgb[125] = new ColorRGB( 135,  67,   0 );
		rgb[126] = new ColorRGB( 135,  69,   0 );
		rgb[127] = new ColorRGB( 135,  72,   0 );
		rgb[128] = new ColorRGB( 135,  73,   0 );
		rgb[129] = new ColorRGB( 135,  76,   0 );
		rgb[130] = new ColorRGB( 135,  78,   0 );
		rgb[131] = new ColorRGB( 135,  80,   0 );
		rgb[132] = new ColorRGB( 135,  82,   0 );
		rgb[133] = new ColorRGB( 135,  84,   0 );
		rgb[134] = new ColorRGB( 135,  87,   0 );
		rgb[135] = new ColorRGB( 135,  88,   0 );
		rgb[136] = new ColorRGB( 135,  90,   0 );
		rgb[137] = new ColorRGB( 135,  93,   0 );
		rgb[138] = new ColorRGB( 135,  95,   0 );
		rgb[139] = new ColorRGB( 135,  98,   0 );
		rgb[140] = new ColorRGB( 135, 101,   0 );
		rgb[141] = new ColorRGB( 135, 103,   0 );
		rgb[142] = new ColorRGB( 135, 106,   0 );
		rgb[143] = new ColorRGB( 135, 107,   0 );
		rgb[144] = new ColorRGB( 135, 110,   0 );
		rgb[145] = new ColorRGB( 135, 113,   0 );
		rgb[146] = new ColorRGB( 135, 115,   0 );
		rgb[147] = new ColorRGB( 135, 118,   0 );
		rgb[148] = new ColorRGB( 135, 121,   0 );
		rgb[149] = new ColorRGB( 135, 124,   0 );
		rgb[150] = new ColorRGB( 135, 127,   0 );
		rgb[151] = new ColorRGB( 135, 129,   0 );
		rgb[152] = new ColorRGB( 135, 133,   0 );
		rgb[153] = new ColorRGB( 135, 135,   0 );
		rgb[154] = new ColorRGB( 135, 138,   0 );
		rgb[155] = new ColorRGB( 135, 141,   0 );
		rgb[156] = new ColorRGB( 135, 144,   0 );
		rgb[157] = new ColorRGB( 135, 148,   0 );
		rgb[158] = new ColorRGB( 135, 150,   0 );
		rgb[159] = new ColorRGB( 135, 155,   0 );
		rgb[160] = new ColorRGB( 135, 157,   0 );
		rgb[161] = new ColorRGB( 135, 160,   0 );
		rgb[162] = new ColorRGB( 135, 163,   0 );
		rgb[163] = new ColorRGB( 135, 166,   0 );
		rgb[164] = new ColorRGB( 135, 170,   0 );
		rgb[165] = new ColorRGB( 135, 174,   0 );
		rgb[166] = new ColorRGB( 135, 177,   0 );
		rgb[167] = new ColorRGB( 135, 180,   0 );
		rgb[168] = new ColorRGB( 135, 184,   0 );
		rgb[169] = new ColorRGB( 135, 188,   0 );
		rgb[170] = new ColorRGB( 135, 192,   0 );
		rgb[171] = new ColorRGB( 135, 195,   0 );
		rgb[172] = new ColorRGB( 135, 200,   0 );
		rgb[173] = new ColorRGB( 135, 203,   0 );
		rgb[174] = new ColorRGB( 135, 205,   0 );
		rgb[175] = new ColorRGB( 135, 210,   0 );
		rgb[176] = new ColorRGB( 135, 214,   0 );
		rgb[177] = new ColorRGB( 135, 218,   0 );
		rgb[178] = new ColorRGB( 135, 222,   0 );
		rgb[179] = new ColorRGB( 135, 226,   0 );
		rgb[180] = new ColorRGB( 135, 231,   0 );
		rgb[181] = new ColorRGB( 135, 236,   0 );
		rgb[182] = new ColorRGB( 135, 239,   0 );
		rgb[183] = new ColorRGB( 135, 244,   0 );
		rgb[184] = new ColorRGB( 135, 249,   0 );
		rgb[185] = new ColorRGB( 135, 254,   0 );
		rgb[186] = new ColorRGB( 135, 255,   1 );
		rgb[187] = new ColorRGB( 135, 255,   5 );
		rgb[188] = new ColorRGB( 135, 255,  10 );
		rgb[189] = new ColorRGB( 135, 255,  15 );
		rgb[190] = new ColorRGB( 135, 255,  20 );
		rgb[191] = new ColorRGB( 135, 255,  23 );
		rgb[192] = new ColorRGB( 135, 255,  28 );
		rgb[193] = new ColorRGB( 135, 255,  33 );
		rgb[194] = new ColorRGB( 135, 255,  38 );
		rgb[195] = new ColorRGB( 135, 255,  43 );
		rgb[196] = new ColorRGB( 135, 255,  45 );
		rgb[197] = new ColorRGB( 135, 255,  49 );
		rgb[198] = new ColorRGB( 135, 255,  54 );
		rgb[199] = new ColorRGB( 135, 255,  59 );
		rgb[200] = new ColorRGB( 135, 255,  65 );
		rgb[201] = new ColorRGB( 135, 255,  70 );
		rgb[202] = new ColorRGB( 135, 255,  74 );
		rgb[203] = new ColorRGB( 135, 255,  80 );
		rgb[204] = new ColorRGB( 135, 255,  84 );
		rgb[205] = new ColorRGB( 135, 255,  90 );
		rgb[206] = new ColorRGB( 135, 255,  95 );
		rgb[207] = new ColorRGB( 135, 255,  98 );
		rgb[208] = new ColorRGB( 135, 255, 104 );
		rgb[209] = new ColorRGB( 135, 255, 110 );
		rgb[210] = new ColorRGB( 135, 255, 116 );
		rgb[211] = new ColorRGB( 135, 255, 120 );
		rgb[212] = new ColorRGB( 135, 255, 125 );
		rgb[213] = new ColorRGB( 135, 255, 131 );
		rgb[214] = new ColorRGB( 135, 255, 137 );
		rgb[215] = new ColorRGB( 135, 255, 144 );
		rgb[216] = new ColorRGB( 135, 255, 149 );
		rgb[217] = new ColorRGB( 135, 255, 154 );
		rgb[218] = new ColorRGB( 135, 255, 158 );
		rgb[219] = new ColorRGB( 135, 255, 165 );
		rgb[220] = new ColorRGB( 135, 255, 172 );
		rgb[221] = new ColorRGB( 135, 255, 179 );
		rgb[222] = new ColorRGB( 135, 255, 186 );
		rgb[223] = new ColorRGB( 135, 255, 191 );
		rgb[224] = new ColorRGB( 135, 255, 198 );
		rgb[225] = new ColorRGB( 135, 255, 203 );
		rgb[226] = new ColorRGB( 135, 255, 211 );
		rgb[227] = new ColorRGB( 135, 255, 216 );
		rgb[228] = new ColorRGB( 135, 255, 224 );
		rgb[229] = new ColorRGB( 135, 255, 232 );
		rgb[230] = new ColorRGB( 135, 255, 240 );
		rgb[231] = new ColorRGB( 135, 255, 248 );
		rgb[232] = new ColorRGB( 135, 255, 254 );
		rgb[233] = new ColorRGB( 135, 255, 255 );
		rgb[234] = new ColorRGB( 140, 255, 255 );
		rgb[235] = new ColorRGB( 146, 255, 255 );
		rgb[236] = new ColorRGB( 153, 255, 255 );
		rgb[237] = new ColorRGB( 156, 255, 255 );
		rgb[238] = new ColorRGB( 161, 255, 255 );
		rgb[239] = new ColorRGB( 168, 255, 255 );
		rgb[240] = new ColorRGB( 172, 255, 255 );
		rgb[241] = new ColorRGB( 177, 255, 255 );
		rgb[242] = new ColorRGB( 182, 255, 255 );
		rgb[243] = new ColorRGB( 189, 255, 255 );
		rgb[244] = new ColorRGB( 192, 255, 255 );
		rgb[245] = new ColorRGB( 199, 255, 255 );
		rgb[246] = new ColorRGB( 204, 255, 255 );
		rgb[247] = new ColorRGB( 210, 255, 255 );
		rgb[248] = new ColorRGB( 215, 255, 255 );
		rgb[249] = new ColorRGB( 220, 255, 255 );
		rgb[250] = new ColorRGB( 225, 255, 255 );
		rgb[251] = new ColorRGB( 232, 255, 255 );
		rgb[252] = new ColorRGB( 236, 255, 255 );
		rgb[253] = new ColorRGB( 240, 255, 255 );
		rgb[254] = new ColorRGB( 248, 255, 255 );
		rgb[255] = new ColorRGB( 255, 255, 255 );
	}
}

