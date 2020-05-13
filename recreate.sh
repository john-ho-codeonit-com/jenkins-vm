#!/usr/bin/env bash

multipass stop jenkins-vm
multipass delete jenkins-vm
multipass purge
multipass launch -n jenkins-vm --cloud-init jenkins.yaml -v
