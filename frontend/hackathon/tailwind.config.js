/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/components/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/app/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  theme: {
    extend: {
      colors: {
        "primary": "#FAAC57",
        "secondary": "#963C08",
        "tertiary": "#D9D9D9",
        "quaternary": "#5B1400",
        "fifth": "#E85716"
      }
    },
  },
  plugins: [],
};
