using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace cs143procgen
{
    class Program
    {
        static void Main(string[] args)
        {
            int numProc, avgCPUBurst, avgArrivalTime, avgPriority;
            Console.WriteLine("Please enter number of processes:");
            numProc = Int32.Parse(Console.ReadLine());

            Console.WriteLine("Please enter avg CPU burst time:");
            avgCPUBurst = Int32.Parse(Console.ReadLine());

            Console.WriteLine("Please enter avg arrival time:");
            avgArrivalTime = Int32.Parse(Console.ReadLine());

            Console.WriteLine("Please enter avg process priority:");
            avgPriority = Int32.Parse(Console.ReadLine());

            using (System.IO.StreamWriter file = new System.IO.StreamWriter(@"input_n"+numProc+"_b"+avgCPUBurst+"_a"+avgArrivalTime+"_p"+avgPriority+".dat", true))
            {
                for (int i = 0; i < numProc; i++)
                {
                    int cpuBurst = Math.Abs((int) TestSimpleRNG.SimpleRNG.GetNormal(avgCPUBurst, 99.0/6.0));
                    int arrivalTime = Math.Abs((int)TestSimpleRNG.SimpleRNG.GetNormal(avgArrivalTime, 69.0/ 6.0));
                    int priority = Math.Abs((int)TestSimpleRNG.SimpleRNG.GetNormal(avgPriority, 9.0 / 6.0));
                    if (cpuBurst <= 0)
                        cpuBurst = 1;

                    if (arrivalTime <= 0)
                        arrivalTime = 0;

                    if (priority <= 0)
                        priority = 0;

                    if (cpuBurst >= 100)
                        cpuBurst = 99;

                    if (arrivalTime >= 70)
                        arrivalTime = 69;

                    if (priority >= 10)
                        priority = 9;

                    
                    file.WriteLine(cpuBurst + " " + arrivalTime + " " + priority);
                }
            }  

        }
    }
}
