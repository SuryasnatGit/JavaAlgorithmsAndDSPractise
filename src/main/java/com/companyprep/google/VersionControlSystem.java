package com.companyprep.google;

/**
 * Implement the version control map system which takes the snapshot of the versions of data. Implement the following
 * functions:
 * 
 * put(key, value) -> puts the value again the key in the latest version of the map
 * 
 * get(key) -> get the value of the key for the latest version of the data
 * 
 * snapshot() -> take a snapshot and increment the version
 * 
 * getValVersion(version id, key) -> return value of the key of the particular version
 * 
 * Example:
 * 
 * Operations
 * 
 * put("memory", "16GB")<br/>
 * put("os_version", "10") <br/>
 * put("graphics", "intel_10_153") <br/>
 * snapshot() <br/>
 * put("os_version", "11") <br/>
 * put("graphics", "intel_11_153") <br/>
 * get("graphics")) <br/>
 * getCurrentVersion()) <br/>
 * getValVersion(0, "graphics")) <br/>
 * get("os_version")) <br/>
 * 
 * Output:
 * 
 * graphics in current version: intel_11_153 <br/>
 * Current Control Map version is : 1 <br/>
 * graphics in version 0 : intel_10_153 <br/>
 * os_version in current version: 11 <br/>
 * 
 * Approach: Use Map of maps
 * 
 * First map will have the version of the control map system as key and the second map as value. <br/>
 * Second map will store the key value pairs of attributes of a particular version of the control map. <br/>
 * Integer currentVersion will represent the latest version at any point of time. <br/>
 * put(key, value) - Insert a key value pair in the second map against the current_version in the first map. <br/>
 * get(key) - Get the value from the second map for the given key against the current_version in the first map <br/>
 * snapshot() - do old_version = current_version. Increment the current_version by 1 and then insert all the values from
 * old_version to current_version. <br/>
 * getValVersion(version_id, key) - Get the value from the second map for the given key against the version_id in the
 * first map. If version_id does not exist, print the error message. <br/>
 * getCurrentVersion() - return the current_version.
 *
 * 
 */
public class VersionControlSystem {

}
