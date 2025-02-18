import Link from "next/link";

const Logo = () => {
  return (
    <Link href="/" scroll={false}>
      <div className="text-white w-52 hover:text-blue-300">
        <svg
          version="1.0"
          xmlns="http://www.w3.org/2000/svg"
          width="1000.000000pt"
          height="151.000000pt"
          viewBox="0 0 1000.000000 151.000000"
          preserveAspectRatio="xMidYMid meet"
          className="w-full h-full"
          fill="currentColor"
        >
          <g
            transform="translate(0.000000,151.000000) scale(0.100000,-0.100000)"
            stroke="none"
            fill="currentColor"
          >
            <path
              d="M80 1341 c-15 -30 -14 -1133 2 -1149 9 -9 89 -12 298 -12 245 0 296
3 356 18 154 41 226 127 254 304 18 113 8 536 -14 607 -33 102 -86 164 -176
205 -81 37 -165 46 -452 46 -244 0 -258 -1 -268 -19z m651 -95 c62 -23 113
-67 141 -123 22 -46 23 -58 26 -331 4 -339 -1 -369 -77 -443 -71 -69 -100 -74
-400 -74 l-255 0 2 495 c1 272 2 497 2 499 0 2 116 1 258 -2 202 -4 267 -8
303 -21z"
            />
            <path
              d="M1332 1348 c-9 -9 -12 -150 -12 -574 0 -309 3 -569 6 -578 5 -14 49
-16 385 -16 l379 0 6 27 c15 58 18 58 -350 61 l-336 2 0 230 0 230 223 0 c122
0 228 4 235 9 8 5 12 22 10 42 l-3 34 -233 3 -232 2 2 223 3 222 329 3 c223 1
334 6 343 13 16 14 17 51 1 67 -17 17 -739 17 -756 0z"
            />
            <path
              d="M2219 1341 l-23 -19 57 -133 c32 -74 145 -330 251 -569 l192 -435 39
0 39 0 54 120 c274 612 444 1010 435 1023 -14 23 -49 32 -66 19 -8 -7 -113
-235 -233 -507 -119 -272 -220 -499 -224 -503 -4 -4 -26 36 -50 90 -263 600
-404 910 -417 921 -22 16 -27 15 -54 -7z"
            />
            <path
              d="M3772 1351 c-15 -9 -482 -1105 -482 -1131 0 -25 25 -42 56 -38 25 3
33 16 93 158 l66 155 301 3 300 2 68 -160 c64 -151 69 -160 95 -160 30 0 51
16 51 38 0 13 -84 216 -377 907 -47 110 -92 208 -100 218 -17 18 -49 22 -71 8z
m176 -476 l118 -280 -254 -3 c-140 -1 -257 0 -260 2 -2 3 53 143 124 311 123
293 129 305 141 278 7 -15 66 -154 131 -308z"
            />
            <path
              d="M4556 1344 c-14 -14 -16 -80 -16 -578 0 -615 -2 -591 57 -584 l28 3
5 240 5 240 245 5 c262 6 284 10 355 64 76 58 115 202 96 356 -19 145 -81 222
-207 255 -87 22 -546 22 -568 -1z m580 -96 c80 -44 108 -114 102 -258 -5 -123
-24 -165 -94 -200 -48 -25 -54 -25 -279 -25 l-230 0 -3 240 c-1 132 0 246 3
253 4 11 48 13 238 10 194 -3 237 -6 263 -20z"
            />
            <path
              d="M5565 1336 c-14 -22 -16 -75 -13 -463 3 -423 4 -440 25 -492 41 -100
85 -145 175 -179 34 -13 91 -17 271 -20 210 -3 235 -2 299 18 123 37 186 119
208 267 5 37 10 244 10 458 0 338 -2 394 -16 413 -19 28 -49 28 -68 0 -14 -19
-16 -79 -16 -443 0 -345 -3 -430 -15 -472 -19 -63 -46 -98 -98 -126 -40 -21
-54 -22 -267 -22 -251 0 -289 6 -343 58 -64 60 -67 87 -67 562 0 364 -2 424
-16 443 -20 29 -50 28 -69 -2z"
            />{" "}
          </g>
        </svg>
      </div>
    </Link>
  );
};

export default Logo;
