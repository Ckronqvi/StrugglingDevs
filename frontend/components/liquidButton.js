import React, { useEffect, useRef } from "react";
import { useRouter } from "next/router";

const LiquidButton = ({ options }) => {
  const canvasRef = useRef(null);

  useEffect(() => {
    const canvas = canvasRef.current;
    const context = canvas.getContext("2d");

    // Get device pixel ratio
    const dpr = window.devicePixelRatio || 1;

    const optionsParam = options || {};
    const tension = optionsParam.tension || 0.4;
    const width = optionsParam.width || 200;
    const height = optionsParam.height || 50;
    const margin = optionsParam.margin || 20;
    const hoverFactor = optionsParam.hoverFactor || -0.1;
    const gap = optionsParam.gap || 5;
    const forceFactor = optionsParam.forceFactor || 0.2;
    const color1 = optionsParam.color1 || "#36DFE7";
    const color2 = optionsParam.color2 || "#d53c76";
    const color3 = optionsParam.color3 || "#E509E6";
    const noise = optionsParam.noise || 0;

    const layers = [
      {
        points: [],
        viscosity: 0.5,
        mouseForce: 100,
        forceLimit: 2,
      },
      {
        points: [],
        viscosity: 0.8,
        mouseForce: 150,
        forceLimit: 3,
      },
    ];

    let touches = [];
    let raf;

    // Set canvas size based on device pixel ratio
    const setCanvasSize = () => {
      const displayWidth = width + margin * 2;
      const displayHeight = height + margin * 2;

      // Set canvas CSS size
      canvas.style.width = `${displayWidth}px`;
      canvas.style.height = `${displayHeight}px`;

      // Set canvas internal size (scaled by dpr)
      canvas.width = displayWidth * dpr;
      canvas.height = displayHeight * dpr;

      // Scale the context to match the high-resolution canvas
      context.scale(dpr, dpr);
    };

    const distance = (p1, p2) =>
      Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));

    const createPoint = (x, y) => ({
      x,
      y,
      ox: x,
      oy: y,
      vx: 0,
      vy: 0,
    });

    const initOrigins = () => {
      layers.forEach((layer) => {
        const points = [];
        for (let x = ~~(height / 2); x < width - ~~(height / 2); x += gap) {
          points.push(createPoint(x + margin, margin));
        }
        for (let alpha = ~~(height * 1.25); alpha >= 0; alpha -= gap) {
          const angle = (Math.PI / ~~(height * 1.25)) * alpha;
          points.push(
            createPoint(
              Math.sin(angle) * (height / 2) + margin + width - height / 2,
              Math.cos(angle) * (height / 2) + margin + height / 2,
            ),
          );
        }
        for (
          let x = width - ~~(height / 2) - 1;
          x >= ~~(height / 2);
          x -= gap
        ) {
          points.push(createPoint(x + margin, margin + height));
        }
        for (let alpha = 0; alpha <= ~~(height * 1.25); alpha += gap) {
          const angle = (Math.PI / ~~(height * 1.25)) * alpha;
          points.push(
            createPoint(
              height - Math.sin(angle) * (height / 2) + margin - height / 2,
              Math.cos(angle) * (height / 2) + margin + height / 2,
            ),
          );
        }
        layer.points = points;
      });
    };

    const update = () => {
      layers.forEach((layer) => {
        const points = layer.points;
        points.forEach((point) => {
          const dx = point.ox - point.x + (Math.random() - 0.5) * noise;
          const dy = point.oy - point.y + (Math.random() - 0.5) * noise;
          const d = Math.sqrt(dx * dx + dy * dy);
          const f = d * forceFactor;
          point.vx += f * (dx / d || 0);
          point.vy += f * (dy / d || 0);
          touches.forEach((touch) => {
            let mouseForce = layer.mouseForce;
            if (
              touch.x > margin &&
              touch.x < margin + width &&
              touch.y > margin &&
              touch.y < margin + height
            ) {
              mouseForce *= -hoverFactor;
            }
            const mx = point.x - touch.x;
            const my = point.y - touch.y;
            const md = Math.sqrt(mx * mx + my * my);
            const mf = Math.max(
              -layer.forceLimit,
              Math.min(layer.forceLimit, (mouseForce * touch.force) / md),
            );
            point.vx += mf * (mx / md || 0);
            point.vy += mf * (my / md || 0);
          });
          point.vx *= layer.viscosity;
          point.vy *= layer.viscosity;
          point.x += point.vx;
          point.y += point.vy;
        });
        points.forEach((point, pointIndex) => {
          const prev = points[(pointIndex + points.length - 1) % points.length];
          const next = points[(pointIndex + points.length + 1) % points.length];
          const dPrev = distance(point, prev);
          const dNext = distance(point, next);
          const line = {
            x: next.x - prev.x,
            y: next.y - prev.y,
          };
          const dLine = Math.sqrt(line.x * line.x + line.y * line.y);
          point.cPrev = {
            x: point.x - (line.x / dLine) * dPrev * tension,
            y: point.y - (line.y / dLine) * dPrev * tension,
          };
          point.cNext = {
            x: point.x + (line.x / dLine) * dNext * tension,
            y: point.y + (line.y / dLine) * dNext * tension,
          };
        });
      });
    };

    const draw = () => {
      context.clearRect(0, 0, canvas.width, canvas.height);
      layers.forEach((layer, layerIndex) => {
        if (layerIndex === 1) {
          if (touches.length > 0) {
            const gx = touches[0].x;
            const gy = touches[0].y;
            layer.color = context.createRadialGradient(
              gx,
              gy,
              height * 2,
              gx,
              gy,
              0,
            );
            layer.color.addColorStop(0, color2);
            layer.color.addColorStop(1, color3);
          } else {
            layer.color = color2;
          }
        } else {
          layer.color = color1;
        }
        const points = layer.points;
        context.fillStyle = layer.color;
        context.beginPath();
        context.moveTo(points[0].x, points[0].y);
        for (let pointIndex = 1; pointIndex < points.length; pointIndex += 1) {
          context.bezierCurveTo(
            points[(pointIndex + 0) % points.length].cNext.x,
            points[(pointIndex + 0) % points.length].cNext.y,
            points[(pointIndex + 1) % points.length].cPrev.x,
            points[(pointIndex + 1) % points.length].cPrev.y,
            points[(pointIndex + 1) % points.length].x,
            points[(pointIndex + 1) % points.length].y,
          );
        }
        context.fill();
      });
    };

    const animate = () => {
      update();
      draw();
      raf = requestAnimationFrame(animate);
    };

    const handleMouseMove = (e) => {
      const rect = canvas.getBoundingClientRect();
      const scaleX = canvas.width / rect.width;
      const scaleY = canvas.height / rect.height;
      touches = [
        {
          x: (e.clientX - rect.left) * scaleX,
          y: (e.clientY - rect.top) * scaleY,
          z: 0,
          force: 1,
        },
      ];
    };

    const handleMouseOut = () => {
      touches = [];
    };

    // Set canvas size and initialize
    setCanvasSize();
    initOrigins();

    // Add event listeners
    canvas.addEventListener("mousemove", handleMouseMove);
    canvas.addEventListener("mouseout", handleMouseOut);

    // Start animation
    animate();

    // Cleanup
    return () => {
      canvas.removeEventListener("mousemove", handleMouseMove);
      canvas.removeEventListener("mouseout", handleMouseOut);
      cancelAnimationFrame(raf);
    };
  }, [options]);

  const router = useRouter();

  const handleClick = () => {
    router.push(options.path || "/register");
  };

  return (
    <button onClick={handleClick} className="relative inline-block">
      <canvas ref={canvasRef} style={{ display: "block" }} />
      <div
        style={{
          position: "absolute",
          top: "50%",
          left: "50%",
          transform: "translate(-50%, -50%)",
          color: options.textColor || "#FFFFFF",
          fontFamily: "M PLUS 1",
          fontWeight: "bold",
          fontSize: options.fontSize || "20px",
          pointerEvents: "none",
        }}
      >
        {options.text || "Submit"}
      </div>
    </button>
  );
};

export default LiquidButton;
