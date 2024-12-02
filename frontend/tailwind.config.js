/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./pages/**/*.{js,ts,jsx,tsx}",
    "./components/**/*.{js,ts,jsx,tsx}",
    "./layouts/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        backGroundBlue: "#00091F",
        white: "#f1f1e6",
        hoverColor: "#ffffff",
      },
      animation: {
        pulse: "pulse 1s cubic-bezier(0.4, 0, 0.6, 1) infinite",
        springBounce: "springBounce 1s ease-in-out infinite",
      },
      keyframes: {
        pulse: {
          "0%, 100%": { opacity: 1 },
          "50%": { opacity: 0.5 },
        },
        springBounce: {
          "0%, 100%": {
            transform: "translateY(0)",
          },
          "30%": {
            transform: "translateY(-20%)",
          },
          "50%": {
            transform: "translateY(5%)",
          },
          "70%": {
            transform: "translateY(-5%)",
          },
        },
      },
    },
    fontFamily: {
      sans: ['"M PLUS 1"', "sans-serif"],
      code: ['"M PLUS 1 Code"', "monospace"],
    },
  },
};