'use client';

import { useMemo, useRef, useCallback } from 'react';

const getRandomPosition = () => ({
  x: Math.random() * 80 + 5,
  y: Math.random() * 85 + 5  
});

const getRandomSize = () => Math.random() * 9 + 5;

const getRandomDelay = () => -(Math.random() * 10); 

const BUBBLE_DISTANCE_THRESHOLD = 20;

const calculateDistance = (pos1, pos2) => {
  return Math.sqrt((pos1.x - pos2.x) ** 2 + (pos1.y - pos2.y) ** 2);
};

const BubbleGradient = ({ color, baseDelay = 0, coordinates, addCoordinate }) => {
  const position = useMemo(() => {
    let newPosition;
    let isTooClose;
    let loopBreaker = 0;
    do {
      loopBreaker++;
      newPosition = getRandomPosition();
      isTooClose = coordinates.some(
        (coord) => calculateDistance(newPosition, coord) < BUBBLE_DISTANCE_THRESHOLD
      );
    } while (isTooClose && loopBreaker < BUBBLE_DISTANCE_THRESHOLD);

    // Call addCoordinate to update coordinates after finding a valid position
    addCoordinate(newPosition);

    return newPosition;
  }, []); // No dependencies to prevent re-renders

  const size = useMemo(getRandomSize, []);
  const delay = useMemo(() => getRandomDelay() + baseDelay, [baseDelay]);

  return (
    <div
      className="absolute rounded-full blur-5xl animate-pulselight"
      style={{
        left: `${position.x}%`,
        top: `${position.y}%`,
        width: `${size}vw`,
        height: `${size}vw`,
        background: color,
        animationDelay: `${delay}s`,
      }}
    />
  );
};

const AnimatedGradientBackground = () => {
  const coordinates = useRef([]);

  // useCallback to ensure reference stability
  const addCoordinate = useCallback((newCoord) => {
    coordinates.current.push(newCoord);
  }, []);

  return (
    <div className="fixed inset-0 -z-50 pointer-events-none overflow-hidden bg-black">
      <div className="absolute inset-0 animate-bubble">
        <div className="absolute inset-0">
          <BubbleGradient color="rgba(30 58 138)" baseDelay={0} coordinates={coordinates.current} addCoordinate={addCoordinate} />
          <BubbleGradient color="rgb(192 38 211)" baseDelay={2} coordinates={coordinates.current} addCoordinate={addCoordinate} />
          <BubbleGradient color="rgb(15 118 110)" baseDelay={4} coordinates={coordinates.current} addCoordinate={addCoordinate} />
          <BubbleGradient color="rgb(30 58 138)" baseDelay={1} coordinates={coordinates.current} addCoordinate={addCoordinate} />
          <BubbleGradient color="rgb(192 38 2)" baseDelay={3} coordinates={coordinates.current} addCoordinate={addCoordinate} />
        </div>
      </div>
    </div>
  );
};

export default AnimatedGradientBackground;
