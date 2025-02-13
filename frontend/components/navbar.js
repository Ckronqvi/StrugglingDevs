import { useState } from "react";
import IconTray from "../components/iconTray";
import LoginButton from "./loginButton";
import Logo from "./logo";
import MobileMenu from "./mobileMenu";
import AnimatedGradientBackground from "./background";

const Navbar = ({ path }) => {
  const [menuOpen, setMenuOpen] = useState(false);

  return (
    <nav className="fixed w-full bg-opacity-50 backdrop-blur-lg z-50 md:pt-6">
      <div className="md:hidden">
        <AnimatedGradientBackground />
      </div>

      <div className="flex items-center justify-evenly p-2">
        <Logo />
        <IconTray path={path} />
        <LoginButton path={path} />

        <div className="flex items-center justify-end flex-1 md:hidden ml-2">
          <button
            className="p-2 rounded-md"
            onClick={() => setMenuOpen(!menuOpen)}
            aria-label="Toggle Menu"
          >
            {menuOpen ? (
              <span className="font-extrabold text-2xl">&#x2715; </span>
            ) : (
              <span className="font-extrabold text-2xl">&#x2630; </span>
            )}
          </button>
        </div>
      </div>

      <MobileMenu menuOpen={menuOpen} setMenuOpen={setMenuOpen} path={path} />
    </nav>
  );
};

export default Navbar;
