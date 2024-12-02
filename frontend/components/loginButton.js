import Link from "next/link";
const LoginButton = ({ path }) => {
  return (
    <div className="hidden md:flex gap-x-2.5 mr-2 pt-1 text-lg w-48 tracking-tight">
      <Link
        href={"/login"}
        className={`${path === "/login" ? "underline underline-offset-4" : ""} hover:{text-hoverColor}  hover:animate-pulse`}
      >
        Sign in
      </Link>
      <Link
        href={"/register"}
        className={`bg-white font-semibold rounded-lg px-1 pb-0.5 text-black ${path === "/register" ? "underline underline-offset-4" : ""} hover:{text-hoverColor}  hover:animate-pulse`}
      >
        Sing up
      </Link>
    </div>
  );
};

export default LoginButton;
