import React, { createContext, useState, useEffect,useContext } from 'react';

export const TimerContext = createContext();

 const TimerProvider = ({ children, initialMinutes = 2 }) => {
  const [minutes, setMinutes] = useState(initialMinutes);
  const [seconds, setSeconds] = useState(0);
  const [isActive, setIsActive] = useState(false);




  useEffect(() => {
    const storedTimer = JSON.parse(localStorage.getItem('timer'));
    if (storedTimer) {
      setMinutes(storedTimer.minutes);
      setSeconds(storedTimer.seconds);
      setIsActive(storedTimer.isActive);
    }


    const handleStorageChange = (event) => {
      if (event.key === 'timer') {
        const updatedTimer = JSON.parse(event.newValue);
        if (updatedTimer) {
          setMinutes(updatedTimer.minutes);
          setSeconds(updatedTimer.seconds);
          setIsActive(updatedTimer.isActive);
        }
      }
    };

    window.addEventListener('storage', handleStorageChange);

    return () => {
      window.removeEventListener('storage', handleStorageChange);
    };
  }, []);

  useEffect(() => {
    if (!isActive) return;

    const deadline = Date.now() + (minutes * 60 + seconds) * 100;

    const updateTimer = () => {
      const timeLeft = deadline - Date.now();
      if (timeLeft <= 0) {
        setMinutes(0);
        setSeconds(0);
        setIsActive(false);
        localStorage.setItem('timer', JSON.stringify({ minutes: 0, seconds: 0, isActive: false }));
        localStorage.removeItem('cart')
        return;
      }
      const newMinutes = Math.floor((timeLeft / 1000 / 60) % 60);
      const newSeconds = Math.floor((timeLeft / 1000) % 60);
      setMinutes(newMinutes);
      setSeconds(newSeconds);

      localStorage.setItem('timer', JSON.stringify({ minutes: newMinutes, seconds: newSeconds, isActive: true }));
    };

    const interval = setInterval(updateTimer, 1000);

    return () => clearInterval(interval);
  }, [isActive, minutes, seconds]);

  const startTimer = () => {
    setIsActive(true);
    localStorage.setItem('timer', JSON.stringify({ minutes, seconds, isActive: true }));
  };

  const stopTimer = () => {
    setIsActive(false);
    localStorage.setItem('timer', JSON.stringify({ minutes, seconds, isActive: false }));
  };

  const RestartTimer = () => {
    setIsActive(false);
    setMinutes(2);
    setSeconds(0);
    localStorage.setItem('timer', JSON.stringify({ minutes, seconds, isActive: false }));
  }

  return (
    <TimerContext.Provider value={{ minutes, seconds, startTimer, stopTimer, isActive,RestartTimer }}>
      {children}
    </TimerContext.Provider>
  );
};
export default TimerProvider;