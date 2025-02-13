import { useState } from "react";
import { useRouter } from "next/router";
import Layout from "../../layouts/article";
import Section from "../../components/section";
import Link from "next/link";

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
  <div className="relative pb-5 group">
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
      className="w-full max-w-xs p-3 rounded-lg text-center bg-white/15 focus:outline-none px-10"
      placeholder={placeholder}
      onFocus={(e) => (e.target.placeholder = "")}
      onBlur={(e) => (e.target.placeholder = placeholder)}
      minLength={minLength}
      autoComplete={autoComplete}
    />
  </div>
);

const Login = () => {
  const [formData, setFormData] = useState({
    email: "",
    username: "",
    password: "",
  });

  const router = useRouter();

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
    <Layout title="Login">
      <div className="flex items-center justify-center flex-col px-6">
        <div className=" bg-gray-500/20 p-10 rounded-lg backdrop-blur-3xl">
          <Section delay={0.2}>
            <h1 className="text-3xl font-bold mb-6 text-center">Sign in</h1>
            <form onSubmit={handleSubmit}>
              <InputField
                label="username"
                name="username"
                type="username"
                value={formData.username}
                onChange={handleInputChange}
                placeholder="Enter username"
                Icon={FaRegUser}
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
              <div className="flex space-x-4 my-4 pt-4">
                <button
                  type="button"
                  onClick={() => router.back()}
                  className="border border-gray-500 font-bold py-3 w-full rounded-lg hover:bg-hoverColor/40 hover:text-black"
                >
                  Back
                </button>
                <button
                  type="submit"
                  className="bg-[#D7BFFF]/20 font-bold py-3 w-full rounded-lg hover:bg-hoverColor/60 hover:text-black"
                >
                  Login
                </button>
              </div>
              <div className="flex min-[450px]:space-x-6 max-[450px]:flex-col max-[450px]:text-center">
                <p>Forgot your password?</p>
                <Link
                  href="\password-recovery"
                  className="font-medium text-blue-400 hover:underline"
                >
                  Reset password
                </Link>
              </div>
              <p className="text-center mt-10 font-medium">OR</p>
              <div className="flex justify-center">
                <button
                  onClick={() => router.push("/register")}
                  className="text-white font-extrabold pt-1 px-3 hover:text-hoverColor text-2xl hover:animate-pulse"
                >
                  Register now
                </button>
              </div>
            </form>
          </Section>
        </div>
      </div>
    </Layout>
  );
};

export default Login;
