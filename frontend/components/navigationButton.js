import { useRouter } from "next/router";

const NavigationButton = ({
  path = "/register",
  buttonText = "Get started",
  Icon = null,
}) => {
  const router = useRouter();

  const handleClick = () => {
    router.push(path);
  };

  return (
    <button
      onClick={handleClick}
      className="bg-white hover:bg-hoverColor border-white border  text-black font-bold py-3 px-8 rounded-full text-2xl inline-flex items-center "
    >
      {Icon && <Icon className="mx-0 mt-0.5" />}
      <span>{buttonText}</span>
    </button>
  );
};

export default NavigationButton;
