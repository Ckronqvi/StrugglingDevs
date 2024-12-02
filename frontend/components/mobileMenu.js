import NextLink from "next/link";

const links = [
  { href: "/profile", label: "Profile" },
  { href: "/", label: "Home" },
  { href: "/projects", label: "Projects" },
  { href: "/developers", label: "Developers" },
  { href: "/faqs", label: "FAQs" },
];

const MobileMenu = ({ menuOpen, path, setMenuOpen }) => {
  if (!menuOpen) return null;

  return (
    <div className="md:hidden">
      <div className="flex flex-col items-center space-y-2 p-4 px-0.5">
        {links.map(({ href, label }) => (
          <NextLink
            key={href}
            href={href}
            className={`p-2 w-full text-center bg-opacity-45 ${
              path === href
                ? "text-black font-extrabold bg-blue-200" +
                  (href === "/" ? " animate-pulse" : "")
                : "text-gray-800 dark:text-white"
            }`}
            onClick={() => {
              setMenuOpen(!menuOpen);
            }}
          >
            {label}
          </NextLink>
        ))}
      </div>
    </div>
  );
};

export default MobileMenu;
