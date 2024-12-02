import { useState } from "react";
import { useRouter } from "next/router";
import Layout from "../../layouts/article";
import Section from "../../components/section";

import { IoMdMail } from "react-icons/io";
import { FaLock, FaRegUser } from "react-icons/fa";

const InputField = ({
  label,
  name,
  type,
  value,
  onChange,
  placeholder,
  Icon,
  minLength,
  autoComplete,
}) => (
  <div className="relative mb-10 mt-10 group ">
    <label
      htmlFor={name}
      className={`pointer-events-none absolute left-2 transition-all duration-200 ease-in-out 
            ${value.length === 0 ? "top-3 pl-28 text-gray-500/0" : "-top-6 text-gray-400 text-md"}`}
    >
      {label}
    </label>
    <Icon
      className="absolute top-3.5 left-3 group-focus-within:animate-springBounce"
      size={22}
      color="gray"
    />
    <input
      type={type}
      name={name}
      value={value}
      onChange={onChange}
      required
      className="w-full max-w-xs p-3 rounded-lg text-center bg-white/15 focus:outline-none px-8"
      placeholder={placeholder}
      onFocus={(e) => (e.target.placeholder = "")}
      onBlur={(e) => (e.target.placeholder = placeholder)}
      minLength={minLength}
      autoComplete={autoComplete}
    />
  </div>
);

const Register = () => {
  const [isStepTwo, setIsStepTwo] = useState(false);
  const [role, setRole] = useState("");
  const [formData, setFormData] = useState({
    email: "",
    username: "",
    password: "",
  });

  const [password2, setPassword2] = useState("");

  const router = useRouter();

  const handleRoleSelection = (selectedRole) => {
    setRole(selectedRole);
    setIsStepTwo(true);
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Form Data Submitted:", formData);
    alert("Registration successful!");
  };

  return (
    <Layout title="Register">
      <div className="flex items-center justify-center flex-col">
        <h1 className="text-3xl font-semibold mb-6">Sign up</h1>
        <div className="bg-gray-500/20 p-10 rounded-lg">
          {!isStepTwo ? (
            <Section delay={0.3}>
              <div className="flex flex-col mx-4">
                <h2 className="text-xl font-bold text-center mb-2">
                  Are you a developer?
                </h2>
                <button
                  onClick={() => handleRoleSelection("developer")}
                  className="mb-14 bg-[#D7BFFF]/20 text-white font-bold py-3 rounded-lg hover:bg-hoverColor/60 hover:text-black"
                >
                  Join as a Developer
                </button>

                <h2 className="text-xl font-bold mb-2 text-center">
                  Need a developer?
                </h2>
                <button
                  onClick={() => handleRoleSelection("business-owner")}
                  className="bg-[#D7BFFF]/20 text-white font-bold py-3 px-3 rounded-lg hover:bg-hoverColor/60 hover:text-black"
                >
                  Get Started as a Business
                </button>

                <p className="text-center mt-20 font-medium">
                  Already a member?
                </p>
                <button
                  onClick={() => router.push("/login")}
                  className="text-white font-extrabold pt-1 px-3 hover:text-hoverColor text-2xl hover:animate-pulse"
                >
                  Log in
                </button>
              </div>
            </Section>
          ) : role === "developer" ? (
            <>
              <h2 className="text-2xl font-bold mb-6 text-center">
                Hi Developer! 👋
              </h2>
              <form onSubmit={handleSubmit}>
                <InputField
                  label="email"
                  name="email"
                  type="email"
                  value={formData.email}
                  onChange={handleInputChange}
                  placeholder="Enter email"
                  Icon={IoMdMail}
                />
                <InputField
                  label="username"
                  name="username"
                  type="text"
                  value={formData.username}
                  onChange={handleInputChange}
                  placeholder="Enter username"
                  Icon={FaRegUser}
                  minLength={4}
                  autoComplete={"off"}
                />
                <InputField
                  label="password"
                  name="password"
                  type="password"
                  value={formData.password}
                  onChange={handleInputChange}
                  placeholder="Enter password"
                  Icon={FaLock}
                  minLength={6}
                />
                <InputField
                  label="repeat password"
                  name="repeatPassword"
                  type="password"
                  value={password2}
                  onChange={(e) => setPassword2(e.target.value)}
                  placeholder="Repeat password"
                  Icon={FaLock}
                />
                <div className="flex space-x-4 mb-6 pt-8">
                  <button
                    type="button"
                    onClick={() => setIsStepTwo(false)}
                    className="border border-gray-500 font-bold py-3 w-full rounded-lg hover:bg-hoverColor/40 hover:text-black"
                  >
                    Back
                  </button>
                  <button
                    type="submit"
                    className="bg-[#D7BFFF]/20 font-bold py-3 w-full rounded-lg hover:bg-hoverColor/60 hover:text-black"
                  >
                    Register
                  </button>
                </div>
              </form>
            </>
          ) : (
            <>
              <h2 className="text-2xl font-bold mb-6 text-center">
                Hi Business Owner!
              </h2>
              <p className="text-center">
                We&apos;ll add more details for business owners later.
              </p>
              <button
                onClick={() => setIsStepTwo(false)}
                className="bg-gray-500 text-white font-bold py-3 rounded-lg hover:bg-gray-700 w-full mt-4"
              >
                Go Back
              </button>
            </>
          )}
        </div>
      </div>
    </Layout>
  );
};

export default Register;
