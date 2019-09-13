package com.companyprep.amazon;

/**
 * James is working on a system that divided appplications into a mixed cluster of computing
 * devices. Each app identified by integer id requires a fixed non 0 amount of memory to execute and
 * is defined to be a fore ground or back ground application. Ids are unique within their own
 * application type but not across types.
 * 
 * Each device should be assigned 2 applications at once One foreground and one background
 * application. Devices have limited amount of memory and cannot exclude applications that require
 * more memory than available memory. The goal is to maximise the total utilization of memory of a
 * given device. A foreground background app pair is considered optimal if there does not exist
 * another pair that uses more memory than this pair and also has a total less than or equal to
 * total memory of the device. For example if the device has 10 mb memory a foreground/background
 * pair using a sum total of 9 mb would be optimal if there dooes not exist a pair that uses a sum
 * total of 10 mb but would not be optimal if such pair exists.
 * 
 * Write an algo to help James find the sets of foreground and background application pairs that
 * optimally utilize the given device for a given list of foreground applications and a given list
 * of background applications.
 * 
 */
public class OptimalMemory {

}
