import { motion } from "framer-motion";

const Section = ({ children, delay = 0 }) => (
  <motion.div
    initial={{ y: 8, opacity: 0 }}
    animate={{ y: 0, opacity: 1 }}
    transition={{ duration: 0.2, delay }}
    style={{ marginBottom: "1.5rem" }}
  >
    {children}
  </motion.div>
);

export default Section;
