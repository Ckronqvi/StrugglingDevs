import "../styles/globals.css";
import { AnimatePresence } from "framer-motion";
import MainLayout from "../layouts/main";

if (typeof window !== "undefined") {
  window.history.scrollRestoration = "manual";
}

function Website({ Component, pageProps, router }) {
  return (
    <>
      <MainLayout router={router}>
        <AnimatePresence
          mode="wait"
          initial={true}
          onExitComplete={() => {
            if (typeof window !== "undefined") {
              window.scrollTo({ top: 0 });
            }
          }}
        >
          <Component {...pageProps} key={router.route} />
        </AnimatePresence>
      </MainLayout>
    </>
  );
}

export default Website;
