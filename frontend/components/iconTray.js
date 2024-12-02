import NextLink from "next/link";

const IconTray = ({ path }) => {
  return (
    <div className="hidden md:flex justify-between flex-grow bg-gray-500/20 max-w-xl rounded-lg px-6 py-1 mx-8 font-bold">
      <NextLink
        href="/"
        className={`rounded-lg p-2 ${path === "/" ? "underline underline-offset-4" : ""} hover:text-hoverColor hover:animate-pulse`}
      >
        Home
      </NextLink>
      <NextLink
        href="/developers"
        className={`rounded-lg p-2 ${path === "/developers" ? "underline underline-offset-4" : ""} hover:text-hoverColor  hover:animate-pulse`}
      >
        Developers
      </NextLink>
      <NextLink
        href="/projects"
        className={`rounded-lg p-2 ${path === "/projects" ? "underline underline-offset-4" : ""} hover:{text-hoverColor}  hover:animate-pulse`}
      >
        Projects
      </NextLink>
      <NextLink
        href="/faqs"
        className={`rounded-lg p-2 ${path === "/faqs" ? "underline underline-offset-4" : ""} hover:text-hoverColor  hover:animate-pulse`}
      >
        FAQs
      </NextLink>
    </div>
  );
};

export default IconTray;
